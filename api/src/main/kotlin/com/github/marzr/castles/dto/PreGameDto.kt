package com.github.marzr.castles.dto

import com.github.marzr.castles.game.PreGame
import kotlinx.serialization.Serializable

@Serializable
data class PreGameDto(val id: Long, val players: List<String>)

fun PreGame.toDto() = PreGameDto(id, players)
