package com.github.marzr.castles.dto

import com.github.marzr.castles.game.Market
import kotlinx.serialization.Serializable

@Serializable
data class MarketDto(
    val tiles: Map<Market.Price, MarketRoomDto?>
)

@Serializable
data class MarketRoomDto(
    val name: String,
    val discount: Int
)

fun Market.toDto() = MarketDto(tiles.mapValues { (_, pair) ->
    pair?.let { (room, discount) ->
        MarketRoomDto(room.title, discount)
    }
})
