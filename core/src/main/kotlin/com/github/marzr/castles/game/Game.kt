package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.data.bonus.KingsFavor
import com.github.marzr.castles.data.rooms.allRooms

@ExperimentalStdlibApi
class Game(private val players: List<Player>) {
    val roomsDeck = Deck(allRooms)
    val bonusDeck = Deck(BonusCard.allBonusCards)

    val kingsFavors = Deck(KingsFavor.allFavors).issue(players.size)
    val marketSize = players.size + 3
    val market = Market().apply {
        addTiles(roomsDeck.issue(marketSize))
    }

    init {
        players.forEach {
            it.money = 15000
        }

        println(market)
    }
}

@ExperimentalStdlibApi
fun main() {
    val p =  Player.PlayerColor.values().map {
        Player("Player ${it.toString().toLowerCase()}", it)
    }
    Game(p)
}
