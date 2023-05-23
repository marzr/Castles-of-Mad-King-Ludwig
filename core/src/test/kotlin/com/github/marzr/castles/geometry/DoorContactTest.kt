package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.DoorRotation.HORIZONTAL
import com.github.marzr.castles.geometry.DoorRotation.VERTICAL
import com.github.marzr.castles.geometry.Position.Rotation.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoorContactTest {

    @Test
    fun `test circles`() {
        val hunding = PositionedTile(roomsByTitle["Хижина Хундинга"]!!, Position(0, 0, R180)) // big circle
        val theater = PositionedTile(roomsByTitle["Театр"]!!, Position(-5, 0, R0)) // big circle
        val piano = PositionedTile(roomsByTitle["Фортепианная"]!!, Position(-4, 3, R90)) // small circle
        val musical = PositionedTile(roomsByTitle["Музыкальный класс"]!!, Position(5, -1, R180)) // small circle
        val greenhouse = PositionedTile(roomsByTitle["Теплица"]!!, Position(0, 5, Position.Rotation.R270))

        assertEquals(setOf(DoorPosition(0, -2, VERTICAL)), doorContact(hunding, theater))
        assertEquals(setOf(DoorPosition(5, -2, VERTICAL)), doorContact(hunding, musical))
        assertEquals(setOf(DoorPosition(2, 0, HORIZONTAL)), doorContact(hunding, greenhouse))
        assertEquals(setOf(DoorPosition(-3, 0, HORIZONTAL)), doorContact(theater, piano))
        assertEquals(emptySet<DoorPosition>(), doorContact(greenhouse, piano))
    }
}
