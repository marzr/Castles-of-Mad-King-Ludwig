package com.github.marzr.castles.game

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.geometry.Position
import com.github.marzr.castles.geometry.Position.Rotation.R0
import com.github.marzr.castles.geometry.Position.Rotation.R270
import com.github.marzr.castles.geometry.PositionedTile
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CastleTest {
    private val foyer = PositionedTile(Foyer(), Position(0, 0, R0)) // 3 3

    @Test
    fun testCircles() {
        val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(3, 0, R0)) //2 2
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(-3, 4, R0)) //4 4
        val pianoRoom = PositionedTile(roomsByTitle["Фортепианная"]!!, Position(5, 0, R270)) //3 3

        val castle = Castle()
        assertTrue(castle.addTile(foyer) is TileAddResult.Success)
        assertTrue(castle.addTile(kitchen) is TileAddResult.Success)
        assertTrue(castle.addTile(garden) is TileAddResult.Success)
        assertTrue(castle.addTile(pianoRoom) is TileAddResult.Success)
    }

    @Test
    fun testAddingOctagons() {
        val castle = Castle()
        val theater = PositionedTile(roomsByTitle["Театр"]!!, Position(4, 5, R0)) //5 5

        assertTrue(castle.addTile(foyer) is TileAddResult.Success)
        assertTrue(castle.addTile(theater) is TileAddResult.Success)
    }
}
