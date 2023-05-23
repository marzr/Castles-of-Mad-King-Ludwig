package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.DoorRotation.HORIZONTAL
import com.github.marzr.castles.geometry.DoorRotation.VERTICAL
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoorPositionTest {

    @Test
    fun `test circles`() {
        val theater = PositionedTile(roomsByTitle["Театр"]!!, Position(-5, 0, Position.Rotation.R0))
        val piano = PositionedTile(roomsByTitle["Фортепианная"]!!, Position(-4, 3, Position.Rotation.R90))
        val musical = PositionedTile(roomsByTitle["Музыкальный класс"]!!, Position(5, -1, Position.Rotation.R180))
        val greenhouse = PositionedTile(roomsByTitle["Теплица"]!!, Position(0, 5, Position.Rotation.R270))
        assertEquals(
            setOf(
                DoorPosition(-5, -2, VERTICAL),
                DoorPosition(-3, 0, HORIZONTAL),
                DoorPosition(0, -2, VERTICAL)
            ), theater.doorPositions().toSet()
        )

        assertEquals(
            setOf(
                DoorPosition(-4, 2, VERTICAL),
                DoorPosition(-1, 2, VERTICAL),
                DoorPosition(-3, 0, HORIZONTAL)
            ), piano.doorPositions().toSet()
        )
        assertEquals(
            setOf(
                DoorPosition(5, -2, VERTICAL),
                DoorPosition(8, -2, VERTICAL),
                DoorPosition(6, -4, HORIZONTAL)
            ), musical.doorPositions().toSet()
        )
        assertEquals(
            setOf(
                DoorPosition(2, 5, HORIZONTAL),
                DoorPosition(2, 0, HORIZONTAL),
            ), greenhouse.doorPositions().toSet()
        )
    }
}
