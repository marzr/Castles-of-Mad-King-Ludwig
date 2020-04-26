package com.github.marzr.castles.game

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.rooms.roomsByTitle
import com.github.marzr.castles.geometry.Position
import com.github.marzr.castles.geometry.PositionedTile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CastleTest {
    private val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(0, 0, Position.Rotation.R0)) //2 2
    private val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(1, 2, Position.Rotation.R0)) //4 4
    private val reception = PositionedTile(roomsByTitle["Приемная"]!!, Position(5, 2, Position.Rotation.R0)) //3 3
    private val reception2 = PositionedTile(roomsByTitle["Приемная"]!!, Position(7, 4, Position.Rotation.R0)) //3 3
    private val theater = PositionedTile(roomsByTitle["Театр"]!!, Position(4, 5, Position.Rotation.R0)) //5 5
    private val gamesRoom = PositionedTile(roomsByTitle["Игровая"]!!, Position(16, 6, Position.Rotation.R0)) //7 2
    private val lounge = PositionedTile(roomsByTitle["Зал приемов"]!!, Position(16, 18, Position.Rotation.R0)) //7 4
    private val foyer = PositionedTile(Foyer(), Position(4, 18, Position.Rotation.R0)) // 3 3
    private val foyer2 = PositionedTile(Foyer(), Position(10, 20, Position.Rotation.R0)) // 3 3
    private val foyer3 = PositionedTile(Foyer(), Position(0, 11, Position.Rotation.R0)) // 3 3


    @Test
    fun testCircles() {
        val castle = Castle()
        val success = TileAddResult.Success(InstantReward(0))
        assertEquals(success, castle.addTile(kitchen))
        assertEquals(success, castle.addTile(garden))
        assertEquals(success, castle.addTile(reception))
        assertEquals(TileAddResult.Fail, castle.addTile(reception2))
        assertEquals(TileAddResult.Fail, castle.addTile(theater))
        assertEquals(success, castle.addTile(gamesRoom))
        assertEquals(success, castle.addTile(lounge))
        assertEquals(success, castle.addTile(foyer))
        assertEquals(success, castle.addTile(foyer2))
        assertEquals(success, castle.addTile(foyer3))
    }

    @Test
    fun testAddingOctagons() {
        val castle = Castle()
        val success = TileAddResult.Success(InstantReward(0))

        assertEquals(success, castle.addTile(theater))
        assertEquals(success, castle.addTile(foyer))
        assertEquals(success, castle.addTile(foyer2))
        assertEquals(success, castle.addTile(foyer3))
    }
}
