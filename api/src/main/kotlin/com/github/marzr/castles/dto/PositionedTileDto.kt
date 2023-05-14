package com.github.marzr.castles.dto

import com.github.marzr.castles.geometry.Position
import kotlinx.serialization.Serializable

@Serializable
data class PositionedTileDto(
    val name: String,
    val positionX: Int,
    val positionY: Int,
    val rotation: Position.Rotation
)
