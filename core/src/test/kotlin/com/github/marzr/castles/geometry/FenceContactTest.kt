package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.rooms.roomsByTitle
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FenceContactTest {

    @Test
    fun test() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(0, 4, Position.Rotation.R180))
        val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(3, 0, Position.Rotation.R0))
        val cabinet = PositionedTile(roomsByTitle["Лиловый кабинет"]!!, Position(4, 3, Position.Rotation.R0))

        assertTrue(garden.hasContactingFence(kitchen))
        assertFalse(garden.hasContactingFence(cabinet))
        assertFalse(kitchen.hasContactingFence(cabinet))
    }
}
