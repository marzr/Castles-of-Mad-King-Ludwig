package com.github.marzr.castles.service

import com.github.marzr.castles.dao.GameDao
import com.github.marzr.castles.dao.JoinedUserDao
import com.github.marzr.castles.game.Game
import com.github.marzr.castles.game.Player

private const val MONEY_TO_RECEIVE_WHEN_PASS_TURN = 5000

@OptIn(ExperimentalStdlibApi::class)
class GameService(
    private val gameDao: GameDao,
    private val joinedUserDao: JoinedUserDao,
    private val playerDbService: PlayerDbService
) {

    private val games: MutableMap<Long, Game> = mutableMapOf()

    fun createGame(preGameId: Long): Game {
        val users = joinedUserDao.getJoinedUsers(preGameId)
        val id = gameDao.create().id.value
        return Game(users, id).also { game ->
            // TODO persist decks
            // TODO persist market
            // TODO persist king favors

            game.market.fullfill(game.roomsDeck) // TODO persist market
            games[id] = game

            game.players.list.forEach {
                it.bonusesToChoose.addAll(game.bonusDeck.issue(3))
            }
            game.players.list.forEach { player -> playerDbService.create(id, player) }
        }
    }

    fun getGame(id: Long): Game? = games[id]

    fun makeTurnGetMoney(gameId: Long, player: Player): Player {
        player.money = player.money + MONEY_TO_RECEIVE_WHEN_PASS_TURN
        playerDbService.plusMoney(player.name, gameId, MONEY_TO_RECEIVE_WHEN_PASS_TURN)
        return player
    }
}
