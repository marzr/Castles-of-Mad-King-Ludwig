package com.github.marzr.castles.dto

import com.github.marzr.castles.game.Player
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    val name: String,
    val money: Int,
    val bonuses: List<BonusCardDto>
)

@Serializable
data class BonusCardDto(
    val id: String
)

fun Player.toDto() = PlayerDto(
    name = name,
    money = money,
    bonuses = bonuses.map { BonusCardDto(it.id) }
)
