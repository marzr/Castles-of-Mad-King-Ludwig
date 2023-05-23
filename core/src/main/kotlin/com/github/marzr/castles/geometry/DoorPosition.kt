package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.*
import com.github.marzr.castles.data.rooms.Door
import com.github.marzr.castles.data.rooms.Door.Side.*
import com.github.marzr.castles.geometry.DoorRotation.HORIZONTAL
import com.github.marzr.castles.geometry.DoorRotation.VERTICAL
import com.github.marzr.castles.geometry.Position.Rotation.*

data class DoorPosition(val x: Int, val y: Int, val rotation: DoorRotation)

enum class DoorRotation {
    HORIZONTAL, VERTICAL
}

fun PositionedTile.doorPositions() = when (this.tile) {
    is Foyer -> TODO()
    is Stairs -> TODO()
    is Hallway -> TODO()
    is RoomTile -> this.tile.doorPosition(position)
    else -> throw IllegalStateException()
}

fun RoomTile.doorPosition(position: Position): List<DoorPosition> = when (val f = this.toFigure(position)) {
    is Circle -> this.doors.map { circleFigureDoorPosition(it, f) }
    is LFigure -> TODO()
    is Octagon -> TODO()
    is Rectangle -> TODO()
}

fun circleFigureDoorPosition(door: Door, circle: Circle) = with(circle.position) {
    val positionsForR0 = listOf(
        DoorPosition(x + circle.diameter / 2, y, HORIZONTAL),
        DoorPosition(x + circle.diameter, y - circle.diameter / 2, VERTICAL),
        DoorPosition(x + circle.diameter / 2, y - circle.diameter, HORIZONTAL),
        DoorPosition(x, y - circle.diameter / 2, VERTICAL)
    )
    when (rotation) {
        R0 -> when (door.side) {
            TOP -> positionsForR0[0]
            RIGHT -> positionsForR0[1]
            BOTTOM -> positionsForR0[2]
            LEFT -> positionsForR0[3]
        }

        R90 -> when (door.side) {
            TOP -> positionsForR0[1]
            RIGHT -> positionsForR0[2]
            BOTTOM -> positionsForR0[3]
            LEFT -> positionsForR0[0]
        }
        R180 -> when (door.side) {
            TOP -> positionsForR0[2]
            RIGHT -> positionsForR0[3]
            BOTTOM -> positionsForR0[0]
            LEFT -> positionsForR0[1]
        }
        R270 -> when (door.side) {
            TOP -> positionsForR0[3]
            RIGHT -> positionsForR0[0]
            BOTTOM -> positionsForR0[1]
            LEFT -> positionsForR0[2]
        }
    }
}
