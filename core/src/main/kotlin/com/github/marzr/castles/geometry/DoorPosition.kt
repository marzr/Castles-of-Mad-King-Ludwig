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

fun PositionedTile.doorPositions(): Set<DoorPosition> = when (this.tile) {
    is Foyer -> with(position) { // expected only RO position
        setOf(
            DoorPosition(x + 1, y, HORIZONTAL),
            DoorPosition(x + 3, y - 1, VERTICAL),
            DoorPosition(x, y - 1, VERTICAL)
        )
    }

    is Stairs -> with(position) {
        if (rotation == R0 || rotation == R180)
            setOf(DoorPosition(x, y, VERTICAL), DoorPosition(x + 3, y, VERTICAL))
        else
            setOf(DoorPosition(x, y, HORIZONTAL), DoorPosition(x, y - 3, HORIZONTAL))
    }

    is Hallway -> hallwayDoorPosition(position)
    is DarkHallway -> hallwayDoorPosition(position)
    is RoomTile -> this.tile.doorPosition(position)
    else -> throw IllegalStateException()
}

fun hallwayDoorPosition(position: Position): Set<DoorPosition> = with(position) {
    if (rotation == R0 || rotation == R180)
        ((0..5).flatMap {
            listOf(DoorPosition(x + it, y, HORIZONTAL), DoorPosition(x + it, y - 1, HORIZONTAL))
        } + listOf(DoorPosition(x, y, VERTICAL), DoorPosition(x + 6, y, VERTICAL))).toSet()
    else
        ((0..5).flatMap {
            listOf(DoorPosition(x, y - it, VERTICAL), DoorPosition(x + 6, y - it, VERTICAL))
        } + listOf(DoorPosition(x, y, HORIZONTAL), DoorPosition(x, y - 6, HORIZONTAL))).toSet()
}

fun RoomTile.doorPosition(position: Position): Set<DoorPosition> = when (val f = this.toFigure(position)) {
    is Circle -> this.doors.map { f.doorPosition(it) }.toSet()
    is LFigure -> TODO()
    is Octagon -> TODO()
    is Rectangle -> this.doors.map { f.doorPosition(it) }.toSet()
}

fun Circle.doorPosition(door: Door) = with(position) {
    val positionsForR0 = listOf(
        DoorPosition(x + diameter / 2, y, HORIZONTAL),
        DoorPosition(x + diameter, y - diameter / 2, VERTICAL),
        DoorPosition(x + diameter / 2, y - diameter, HORIZONTAL),
        DoorPosition(x, y - diameter / 2, VERTICAL)
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

fun Rectangle.doorPosition(door: Door): DoorPosition = with(position) {
    when (rotation) {
        R0 -> when (door.side) {
            TOP -> DoorPosition(x + door.index - 1, y, HORIZONTAL)
            RIGHT -> DoorPosition(x + width, y - door.index + 1, VERTICAL)
            BOTTOM -> DoorPosition(x + door.index - 1, y - height, HORIZONTAL)
            LEFT -> DoorPosition(x, y - door.index + 1, VERTICAL)
        }

        R90 -> when (door.side) {
            TOP -> DoorPosition(x + height, y - door.index + 1, VERTICAL)
            RIGHT -> DoorPosition(x - door.index + height, y - width, HORIZONTAL)
            BOTTOM -> DoorPosition(x, y - door.index + 1, VERTICAL)
            LEFT -> DoorPosition(x + height - door.index, y, HORIZONTAL)
        }

        R180 -> when (door.side) {
            TOP -> DoorPosition(x + width - door.index, y - height, HORIZONTAL)
            RIGHT -> DoorPosition(x, y - height + door.index, VERTICAL)
            BOTTOM -> DoorPosition(x + width - door.index, y, HORIZONTAL)
            LEFT -> DoorPosition(x + width, y - height + door.index, VERTICAL)
        }

        R270 -> when (door.side) {
            TOP -> DoorPosition(x, y - width + door.index, VERTICAL)
            RIGHT -> DoorPosition(x + door.index - 1, y, HORIZONTAL)
            BOTTOM -> DoorPosition(x + height, y - width + door.index, VERTICAL)
            LEFT -> DoorPosition(x + door.index - 1, y - width, HORIZONTAL)
        }
    }
}
