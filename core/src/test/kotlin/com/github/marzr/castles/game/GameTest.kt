package com.github.marzr.castles.game

import com.github.marzr.castles.DbTest
import com.github.marzr.castles.dao.*
import com.github.marzr.castles.service.GameService
import com.github.marzr.castles.service.MarketDbService
import com.github.marzr.castles.service.PlayerDbService
import com.github.marzr.castles.service.PreGameDbService
import org.junit.jupiter.api.Test

@OptIn(ExperimentalStdlibApi::class)
class GameTest : DbTest {

    val gameService = GameService(
        GameDao(), JoinedUserDao(), PlayerDbService(PlayerDao(), BonusCardDao()),
        MarketDbService(MarketDao()), KingFavorDao()
    )

    val preGameDbService = PreGameDbService(PreGameDao(), JoinedUserDao())

    @Test
    fun `test init game`() {
        Game(listOf("1", "2", "3", "4"), 1)
        Game(listOf("1", "2", "3"), 2)
        Game(listOf("1", "2"), 3)
    }

    @Test
    fun `test game one turn`() {
        val preGame = preGameDbService.create("p1")
        preGameDbService.join("p2", preGame.id)
        gameService.createGame(preGame.id).apply {
            players.list.forEach {
                it.bonuses.addAll(it.bonusesToChoose.take(2))
                it.bonusesToChoose.clear()
            }
            startGame()

            val buyer = players.currentBuyer()
            market.buy(buyer, players.builder(), Market.Price.PRICE_4000)
            market.buy(players.builder(), players.builder(), Market.Price.PRICE_6000)
            gameService.nextTurn(this)
        }
    }
}
