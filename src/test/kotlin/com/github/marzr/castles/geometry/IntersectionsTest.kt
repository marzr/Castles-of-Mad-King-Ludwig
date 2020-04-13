package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.Stairs
import com.github.marzr.castles.data.rooms.roomsByTitle
import com.github.marzr.castles.geometry.Position.Rotation.R0
import com.github.marzr.castles.geometry.Position.Rotation.R90
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IntersectionsTest {

    @Test
    fun test() {
        val foyer = PositionedTile(Foyer(), Position(0, 0, R0))
        val stairs = PositionedTile(Stairs(), Position(1, 3, R90))
        assertFalse(foyer intersects stairs)
    }

    @Test
    fun `square inside square`() {
        val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(1, 3, R0))
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(0, 4, R0))
        assertTrue(kitchen intersects garden)
    }
}
