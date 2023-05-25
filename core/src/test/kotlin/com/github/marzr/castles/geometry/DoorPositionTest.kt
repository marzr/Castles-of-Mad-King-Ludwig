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
            ), theater.doorPositions()
        )

        assertEquals(
            setOf(
                DoorPosition(-4, 2, VERTICAL),
                DoorPosition(-1, 2, VERTICAL),
                DoorPosition(-3, 0, HORIZONTAL)
            ), piano.doorPositions()
        )
        assertEquals(
            setOf(
                DoorPosition(5, -2, VERTICAL),
                DoorPosition(8, -2, VERTICAL),
                DoorPosition(6, -4, HORIZONTAL)
            ), musical.doorPositions()
        )
        assertEquals(
            setOf(
                DoorPosition(2, 5, HORIZONTAL),
                DoorPosition(2, 0, HORIZONTAL),
            ), greenhouse.doorPositions()
        )
    }

    @Test
    fun `rectangle test R0`() {
        val garden = PositionedTile(roomsByTitle["Парадные сады"]!!, Position(0, 5, Position.Rotation.R0))
        assertEquals(
            setOf(
                DoorPosition(4, 2, HORIZONTAL),
                DoorPosition(6, 5, VERTICAL),
                DoorPosition(6, 3, VERTICAL)
            ), garden.doorPositions()
        )
    }

    @Test
    fun `rectangle test R90`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(1, 5, Position.Rotation.R90))
        assertEquals(
            setOf(
                DoorPosition(3, 5, HORIZONTAL),
                DoorPosition(4, 1, HORIZONTAL),
                DoorPosition(1, 2, VERTICAL)
            ), garden.doorPositions()
        )
    }

    @Test
    fun `rectangle test R180`() {
        val hall = PositionedTile(roomsByTitle["Главный зал"]!!, Position(-2, 2, Position.Rotation.R180))
        assertEquals(
            setOf(
                DoorPosition(-2, 2, HORIZONTAL),
                DoorPosition(0,2, HORIZONTAL),
                DoorPosition(2, 2, HORIZONTAL),
                DoorPosition(4, 2, HORIZONTAL),
                DoorPosition(-2, 2, VERTICAL),
                DoorPosition(-2, 1, VERTICAL),
                DoorPosition(5, 2, VERTICAL),
                DoorPosition(5, 1, VERTICAL),
                DoorPosition(-1, 0, HORIZONTAL),
                DoorPosition(1, 0, HORIZONTAL),
                DoorPosition(3, 0, HORIZONTAL)
            ), hall.doorPositions()
        )
    }

    @Test
    fun `rectangle test R270`() {
        val artRoom = PositionedTile(roomsByTitle["Художественный класс"]!!, Position(-1, 4, Position.Rotation.R270))
        assertEquals(
            setOf(
                DoorPosition(-1, 4, HORIZONTAL),
                DoorPosition(2,4, VERTICAL),
                DoorPosition(-1, 1, VERTICAL),
                DoorPosition(0, -2, HORIZONTAL)
            ), artRoom.doorPositions()
        )
    }
}
