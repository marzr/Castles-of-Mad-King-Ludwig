package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.roomsByTitle
import com.github.marzr.castles.game.Player
import com.github.marzr.castles.geometry.Position.Rotation.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FenceContactTest {

    @Test
    fun `test squares`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(0, 4, R180))
        val kitchen = PositionedTile(roomsByTitle["Кухонька"]!!, Position(3, 0, R0))
        val queenRoom = PositionedTile(roomsByTitle["Спальня королевы"]!!, Position(-3, 2, R0))
        val cabinet = PositionedTile(roomsByTitle["Лиловый кабинет"]!!, Position(4, 3, R0))

        assertTrue(garden.hasContactingFence(kitchen))
        assertTrue(garden.hasContactingFence(queenRoom))
        assertFalse(garden.hasContactingFence(cabinet))
        assertFalse(kitchen.hasContactingFence(cabinet))
    }

    @Test
    fun `test octagons`() {
        val garden = PositionedTile(roomsByTitle["Конюшни"]!!, Position(0, 1, R90))
        val foyer1 = PositionedTile(Foyer(Player.PlayerColor.RED), Position(3, 3, R0))
        val foyer2 = PositionedTile(Foyer(Player.PlayerColor.BLUE), Position(-2, 3, R270))

        assertTrue(garden.hasContactingFence(foyer1))
        assertFalse(garden.hasContactingFence(foyer2))
    }

    @Test
    fun `test big circles`() {
        val garden = PositionedTile(roomsByTitle["Хижина Хундинга"]!!, Position(0, 0, R0))
        val theatre = PositionedTile(roomsByTitle["Театр"]!!, Position(0, 3, R180))

        assertFalse(garden.hasContactingFence(theatre))
    }

    @Test
    fun `test middle rectangles`() {
        val garden = PositionedTile(roomsByTitle["Привратницкая"]!!, Position(0, 0, R0))
        val pantry = PositionedTile(roomsByTitle["Кладовая"]!!, Position(4, 2, R270))
        val easternTapestryHall = PositionedTile(roomsByTitle["Восточный гобеленный зал"]!!, Position(5, -2, R90))

        assertTrue(garden.hasContactingFence(pantry))
        assertFalse(garden.hasContactingFence(easternTapestryHall))
    }

    @Test
    fun `test large rectangles`() {
        val garden = PositionedTile(roomsByTitle["Каретный гараж"]!!, Position(-6, 0, R0))
        val westernTapestryHall = PositionedTile(roomsByTitle["Западный гобеленный зал"]!!, Position(-1, 6, R270))
        val arsenal = PositionedTile(roomsByTitle["Арсенал"]!!, Position(0, -3, R90))

        assertTrue(garden.hasContactingFence(westernTapestryHall))
        assertFalse(garden.hasContactingFence(arsenal))
    }

    @Test
    fun `test small circles`() {
        val garden = PositionedTile(roomsByTitle["Привратницкая"]!!, Position(0, 2, R0))
        val kingsOffice = PositionedTile(roomsByTitle["Кабинет короля"]!!, Position(1, 5, R0))
        val pianoRoom = PositionedTile(roomsByTitle["Фортепианная"]!!, Position(5, 3, R180))

        assertTrue(garden.hasContactingFence(kingsOffice))
        assertFalse(garden.hasContactingFence(pianoRoom))
    }

    @Test
    fun `test l rooms`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(-2, 2, R0))
        val sauna = PositionedTile(roomsByTitle["Сауна"]!!, Position(-5, 6, R270))
        val diningHall = PositionedTile(roomsByTitle["Обеденный зал"]!!, Position(2, 6, R0))

        assertTrue(garden.hasContactingFence(sauna))
        assertFalse(garden.hasContactingFence(diningHall))
    }

    @Test
    fun `test small squares`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(-1, 2, R180))
        val wardrobe = PositionedTile(roomsByTitle["Гардероб"]!!, Position(-2, -2, R0))
        val oilStorage = PositionedTile(roomsByTitle["Склад масла"]!!, Position(3, -2, R90))
        val pinkOffice = PositionedTile(roomsByTitle["Розовый кабинет"]!!, Position(3, 4, R0))

        assertTrue(garden.hasContactingFence(wardrobe))
        assertFalse(garden.hasContactingFence(oilStorage))
        assertFalse(garden.hasContactingFence(pinkOffice))
    }

    @Test
    fun `test long rectangles`() {
        val garden = PositionedTile(roomsByTitle["Каретный гараж"]!!, Position(-3, 2, R0))
        val mainHall = PositionedTile(roomsByTitle["Главный зал"]!!, Position(2, 4, R180))
        val mirrorGallery = PositionedTile(roomsByTitle["Галерея зеркал"]!!, Position(-5, 2, R270))

        assertTrue(garden.hasContactingFence(mainHall))
        assertFalse(garden.hasContactingFence(mirrorGallery))
    }

    @Test
    fun `test small rectangles when garden R90`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(1, 2, R90))
        val tanningBed = PositionedTile(roomsByTitle["Солярий"]!!, Position(5, 5, R270))
        val bathroom = PositionedTile(roomsByTitle["Ванная"]!!, Position(5, -2, R0))

        assertTrue(garden.hasContactingFence(tanningBed))
        assertFalse(garden.hasContactingFence(bathroom))
    }

    @Test
    fun `test small rectangles when garden R0`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(-4, -3, R0))
        val tanningBed = PositionedTile(roomsByTitle["Солярий"]!!, Position(-7, -1, R0))

        assertTrue(garden.hasContactingFence(tanningBed))
    }

    @Test
    fun `test small rectangles when garden R270`() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(-4, 4, R270))
        val tanningBed = PositionedTile(roomsByTitle["Солярий"]!!, Position(-6, 7, R270))

        assertTrue(garden.hasContactingFence(tanningBed))
    }
}
