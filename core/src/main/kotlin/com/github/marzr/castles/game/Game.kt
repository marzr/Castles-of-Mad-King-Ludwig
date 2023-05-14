package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.data.bonus.KingsFavor
import com.github.marzr.castles.data.rooms.allRooms

@ExperimentalStdlibApi
class Game(playersCount: Int) {
    val roomsDeck = Deck(allRooms)
    val bonusDeck = Deck(BonusCard.allBonusCards)

    val kingsFavors = Deck(KingsFavor.allFavors).issue(playersCount)

    val market = Market(playersCount)
    val players = Players(playersCount)

    init {
        players.list.forEach {
            it.money = 15000
        }

        market.fullfill(roomsDeck)
    }

    fun nextTurn() {
        market.fullfill(roomsDeck)
        players.nextTurn()
    }
}

@ExperimentalStdlibApi
fun main() {

    val game = Game(4)
    println(game.kingsFavors)
    println(game.market)

    val buyer = game.players.currentBuyer()
    game.market.buy(buyer, game.players.builder(), Market.Price.PRICE_1000)

    println(game.market)

    println(game.players.builder())
    println(buyer)
    game.nextTurn()
    println("----------------")

    println(game.players.builder())
}
