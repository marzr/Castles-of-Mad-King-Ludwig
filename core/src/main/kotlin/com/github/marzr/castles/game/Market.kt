package com.github.marzr.castles.game

import com.github.marzr.castles.data.RoomTile
import java.lang.IllegalStateException

class Market(playersCount: Int) {
    private val size = playersCount + 3
    var tilesMap: MutableMap<Price, Pair<RoomTile, Int>?> =
        Price.values().takeLast(size).associateWith { null }.toMutableMap()

    val tiles get() = tilesMap as Map<Price, Pair<RoomTile, Int>?>

    @OptIn(ExperimentalStdlibApi::class)
    fun fullfill(deck: Deck<RoomTile>) {
        tilesMap.forEach { (price, _) ->
            tilesMap[price]?.let {
                val (room, discount) = it
                tilesMap[price] = room to discount + 1000
            }
        }

        val newRooms = deck.issue(size - tilesMap.values.filterNotNull().size).toMutableList()
        tilesMap.forEach { (price, pair) ->
            if (pair == null) {
                tilesMap[price] = newRooms.removeLast() to 0
            }
        }
    }

    override fun toString(): String {
        return "Market($tilesMap)"
    }

    enum class Price(val amount: Int) {
        PRICE_1000(1000),
        PRICE_2000(2000),
        PRICE_4000(4000),
        PRICE_6000(6000),
        PRICE_8000(8000),
        PRICE_10000(10000),
        PRICE_15000(15000)
    }
}

