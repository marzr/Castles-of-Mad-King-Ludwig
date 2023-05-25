package com.github.marzr.castles.service

import com.github.marzr.castles.dao.*
import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.game.Game
import com.github.marzr.castles.game.Market
import com.github.marzr.castles.game.Player
import com.github.marzr.castles.game.Players
import com.github.marzr.castles.geometry.Position
import com.github.marzr.castles.geometry.Position.Rotation.R0
import com.github.marzr.castles.geometry.PositionedTile
import java.lang.IllegalStateException

private const val MONEY_TO_RECEIVE_WHEN_PASS_TURN = 5000

@OptIn(ExperimentalStdlibApi::class)
class GameService(
    private val gameDao: GameDao,
    private val joinedUserDao: JoinedUserDao,
    private val playerDbService: PlayerDbService,
    private val marketDbService: MarketDbService,
    private val kingFavorDao: KingFavorDao,
    private val roomDao: RoomDao,
    private val bonusCardDao: BonusCardDao
) {

    companion object {
        const val STAIRS_PRICE = 3000
        const val HALLWAY_PRICE = 3000
    }

    private val games: MutableMap<Long, Game> = mutableMapOf()

    fun createGame(preGameId: Long): Game {
        val users = joinedUserDao.getJoinedUsers(preGameId)
        val id = gameDao.create(
            remainingStairs = Game.STAIRS_COUNT[users.size]!!,
            remainingHallways = Game.HALLWAY_COUNT[users.size]!!
        ).id.value
        return Game(users, id).also { game ->
            // TODO persist decks

            game.kingsFavors.forEach {
                kingFavorDao.create(id, it.id)
            }

            game.market.fullfill(game.roomsDeck)
            marketDbService.create(id, game.market)

            games[id] = game

            game.players.list.forEach {
                it.bonusesToChoose.addAll(game.bonusDeck.issue(3))
            }

            val players = Player.PlayerColor.values().take(users.size).zip(users).map { (color, name) ->
                val playerId = playerDbService.create(game.id, name, Players.INITIAL_MONEY_AMOUNT, color)
                Player(playerId, name, Players.INITIAL_MONEY_AMOUNT, color).also {
                    it.bonusesToChoose.forEach {
                        bonusCardDao.addBonus(it.id, playerId, toChoose = true)
                    }
                }
            }
            game.players.list = players

            game.players.list.forEach {
                val tile = PositionedTile(Foyer(it.color), Position(0, 0, R0))
                it.castle.addTile(tile)
                with(tile.position) { roomDao.create(it.id, tile.tile.title, x, y, rotation.name) }
            }
        }
    }

    fun getGame(id: Long): Game? = games[id]

    fun nextTurn(game: Game) {
        game.checkBonusesChosen()

        game.market.fullfill(game.roomsDeck)
        marketDbService.persistFullfill(game.id, game.market)

        game.players.nextTurn()// TODO persist
    }

    fun makeTurnGetMoney(gameId: Long, player: Player): Player {
        player.money = player.money + MONEY_TO_RECEIVE_WHEN_PASS_TURN
        playerDbService.plusMoney(player.name, gameId, MONEY_TO_RECEIVE_WHEN_PASS_TURN)
        return player
    }

    fun buyTile(game: Game, buyer: Player, seller: Player, price: Market.Price): RoomTile {
        val (room, discount) = game.market.tilesMap[price] ?: throw IllegalStateException("$buyer wants buy null")
        game.market.tilesMap[price] = null
        marketDbService.removeTile(game.id, price)

        buyer.money -= price.amount
        playerDbService.minusMoney(buyer.name, game.id, price.amount)

        if (buyer.money < 0) throw IllegalStateException("$buyer money < 0")
        buyer.money += discount
        playerDbService.plusMoney(buyer.name, game.id, discount)

        if (buyer != seller) {
            seller.money += price.amount
            playerDbService.plusMoney(seller.name, game.id, price.amount)
        }
        return room
    }

    fun buyStairs(game: Game, buyer: Player, seller: Player) {
        buyer.money -= STAIRS_PRICE
        playerDbService.minusMoney(buyer.name, game.id, STAIRS_PRICE)

        if (buyer != seller) {
            seller.money += STAIRS_PRICE
            playerDbService.plusMoney(seller.name, game.id, STAIRS_PRICE)
        }
        game.remainingStairs -= 1
    }

    fun buyHallway(game: Game, buyer: Player, seller: Player) {
        buyer.money -= HALLWAY_PRICE
        playerDbService.minusMoney(buyer.name, game.id, HALLWAY_PRICE)

        if (buyer != seller) {
            seller.money += HALLWAY_PRICE
            playerDbService.plusMoney(seller.name, game.id, HALLWAY_PRICE)
        }
        game.remainingHallways -= 1
    }
}
