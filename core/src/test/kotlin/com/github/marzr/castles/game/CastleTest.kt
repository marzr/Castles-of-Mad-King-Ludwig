package com.github.marzr.castles.game

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.Position
import com.github.marzr.castles.geometry.PositionedTile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CastleTest {
    private val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(0, 0, Position.Rotation.R0)) //2 2
    private val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(1, 4, Position.Rotation.R0)) //4 4
    private val reception = PositionedTile(roomsByTitle["Приемная"]!!, Position(5, 2, Position.Rotation.R0)) //3 3
    private val theater = PositionedTile(roomsByTitle["Театр"]!!, Position(4, 5, Position.Rotation.R0)) //5 5
    private val foyer = PositionedTile(Foyer(), Position(4, 18, Position.Rotation.R0)) // 3 3

    @Test
    fun testCircles() {
        val castle = Castle()
        val success = TileAddResult.Success(InstantReward(0))
        assertEquals(success, castle.addTile(kitchen))
        assertEquals(success, castle.addTile(garden))
        assertEquals(success, castle.addTile(reception))
    }

    @Test
    fun testAddingOctagons() {
        val castle = Castle()

        assertTrue(castle.addTile(theater) is TileAddResult.Success)
        assertTrue(castle.addTile(foyer) is TileAddResult.Success)
    }
}
