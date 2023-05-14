package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.data.bonus.KingsFavor
import com.github.marzr.castles.data.rooms.allRooms
import java.lang.IllegalStateException

@ExperimentalStdlibApi
class Game(playersCount: Int) {
    private val roomsDeck = Deck(allRooms)
    private val bonusDeck = Deck(BonusCard.allBonusCards)

    val kingsFavors = Deck(KingsFavor.allFavors).issue(playersCount)

    val market = Market(playersCount)
    val players = Players(playersCount)

    init {
        players.list.forEach {
            it.money = 15000
        }

        players.list.forEach {
            it.bonusesToChoose.addAll(bonusDeck.issue(3))
        }

        market.fullfill(roomsDeck)
    }

    fun startGame() {
        checkBonusesChosen()
    }

    private fun checkBonusesChosen() {
        if (players.list.find { it.bonusesToChoose.isNotEmpty() } != null)
            throw IllegalStateException("Players should choose bonus cards")
    }

    fun nextTurn() {
        checkBonusesChosen()
        market.fullfill(roomsDeck)
        players.nextTurn()
    }
}

@ExperimentalStdlibApi
fun main() {
    with(Game(4)) {
        players.list.forEach {
            it.bonuses.addAll(it.bonusesToChoose.take(2))
            it.bonusesToChoose.clear()
        }
        startGame()
        println(kingsFavors)
        println(market)

        val buyer = players.currentBuyer()
        market.buy(buyer, players.builder(), Market.Price.PRICE_1000)

        println(market)

        println(players.builder())
        println(buyer)
        nextTurn()
        println("----------------")

        println(players.builder())
    }
}
