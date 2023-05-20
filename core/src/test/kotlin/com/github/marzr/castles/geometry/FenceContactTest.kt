package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.Position.Rotation.R0
import com.github.marzr.castles.geometry.Position.Rotation.R180
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FenceContactTest {

    @Test
    fun test() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(0, 4, R180))
        val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(3, 0, R0))
        val queenRoom = PositionedTile(roomsByTitle["Спальня королевы"]!!, Position(-3, 2, R0))
        val cabinet = PositionedTile(roomsByTitle["Лиловый кабинет"]!!, Position(4, 3, R0))

        assertTrue(garden.hasContactingFence(kitchen))
        assertTrue(garden.hasContactingFence(queenRoom))
        assertFalse(garden.hasContactingFence(cabinet))
        assertFalse(kitchen.hasContactingFence(cabinet))
    }
}
