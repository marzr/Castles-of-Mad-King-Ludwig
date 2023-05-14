package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.data.bonus.KingsFavor
import com.github.marzr.castles.data.rooms.allRooms
import java.util.*

@ExperimentalStdlibApi
class Game(playersCount: Int) {
    val roomsDeck = Deck(allRooms)
    val bonusDeck = Deck(BonusCard.allBonusCards)

    val kingsFavors = Deck(KingsFavor.allFavors).issue(playersCount)

    val market = Market(playersCount)
    val points = mutableMapOf<Player, Int>()
    val players = Players(playersCount)

    init {
        players.list.forEach {
            it.money = 15000
        }

        market.fullfill(roomsDeck)
    }

    fun nextTurn() {
        market.fullfill(roomsDeck)
        players.next()
    }
}

@ExperimentalStdlibApi
fun main() {

    val game = Game(4)
    println(game.kingsFavors)
    println(game.market)

    game.market.buy(game.players.current(), Market.Price.PRICE_1000)

    println(game.market)

    println(game.players.current())

    game.nextTurn()
    println("----------------")

    println(game.players.current())
}
