package com.github.marzr.castles.dto

import com.github.marzr.castles.game.Game
import kotlinx.serialization.Serializable

@Serializable
data class GameDto(
    val id: Long,
    val kingsFavors: List<KingsFavorDto>,
    val market: MarketDto,
    val builder: String,
    val buyer: String?,
)

@OptIn(ExperimentalStdlibApi::class)
fun Game.toDto() = GameDto(
    id = id,
    kingsFavors = kingsFavors.map { KingsFavorDto(it.id) },
    market = market.toDto(),
    builder = this.players.builder().name,
    buyer = this.players.currentBuyer().name,
)

@Serializable
data class KingsFavorDto(val id: String)
