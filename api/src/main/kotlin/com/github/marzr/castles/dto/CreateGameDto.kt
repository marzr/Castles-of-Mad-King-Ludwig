package com.github.marzr.castles.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameDto(
    val preGameId: Long
)
