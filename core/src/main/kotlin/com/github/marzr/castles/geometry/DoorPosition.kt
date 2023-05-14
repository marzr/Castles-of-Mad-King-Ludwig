package com.github.marzr.castles.geometry

sealed interface DoorPosition {
    data class Horizontal(val y: Int, val x: Int) : DoorPosition
    data class Vertical(val x: Int, val y: Int) : DoorPosition
}
