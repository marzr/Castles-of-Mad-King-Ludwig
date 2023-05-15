package com.github.marzr.castles.dto

import kotlinx.serialization.Serializable

@Serializable
data class GameSettingsDto(
    val playersCount: Int
)