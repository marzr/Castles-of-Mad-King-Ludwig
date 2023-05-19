package com.github.marzr.castles.geometry

data class DoorPosition(val x: Int, val y: Int, val rotation: DoorRotation)

enum class DoorRotation {
    HORIZONTAL, VERTICAL
}
