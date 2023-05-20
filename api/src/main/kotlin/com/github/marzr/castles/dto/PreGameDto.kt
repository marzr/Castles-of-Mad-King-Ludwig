package com.github.marzr.castles.dto

import com.github.marzr.castles.game.PreGame
import kotlinx.serialization.Serializable

@Serializable
data class PreGameDto(val id: Long)

fun PreGame.toDto() = PreGameDto(id)
