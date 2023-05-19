package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.*
import com.github.marzr.castles.data.rooms.RoomTile

sealed interface Figure

class Octagon(val width: Int, val height: Int, val position: Position) : Figure

data class Rectangle(val width: Int, val height: Int, val position: Position) : Figure

data class Circle(val diameter: Int, val position: Position) : Figure

data class LFigure(val position: Position) : Figure

fun PositionedTile.toFigure(): Figure = when (val tile = this.tile) {
    is RoomTile -> tile.toFigure(position)
    is Foyer -> Octagon(3, 3, position)
    is Stairs -> Rectangle(3, 1, position)
    is Hallway -> TODO()
}

fun RoomTile.toFigure(position: Position): Figure = when (this.figureType) {
    FigureType.BIG_CIRCLE -> Circle(5, position)
    FigureType.SMALL_CIRCLE -> Circle(3, position)
    FigureType.SQUARE -> Rectangle(4, 4, position)
    FigureType.SMALL_SQUARE -> Rectangle(2, 2, position)
    FigureType.LARGE_OCTAGON -> Octagon(7, 4, position)
    FigureType.L_ROOM -> LFigure(position)
    FigureType.LARGE_RECTANGLE -> Rectangle(6, 3, position)
    FigureType.LONG_RECTANGLE -> Rectangle(7, 2, position)
    FigureType.MIDDLE_RECTANGLE -> Rectangle(5, 2, position)
    FigureType.SMALL_RECTANGLE -> Rectangle(4, 2, position)
}
