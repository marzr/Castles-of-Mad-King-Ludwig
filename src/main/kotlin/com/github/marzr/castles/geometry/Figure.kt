package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.Stairs
import com.github.marzr.castles.data.rooms.RoomTile

interface Figure

class Square(side: Int, position: Position) : Rectangle(side, side, position)

open class Rectangle(val width: Int, val height: Int, val position: Position) : Figure

class Circle(val diameter: Int, position: Position) : Figure


fun PositionedTile.toFigure(): Figure = when (val tile = this.tile) {
    is RoomTile -> tile.toFigure(this.position)
    is Foyer -> Square(3, position) // TODO this is not a square
    is Stairs -> Rectangle(3, 1, position)
    else -> TODO()
}

fun RoomTile.toFigure(position: Position): Figure = when (this.figureType) {
    FigureType.BIG_CIRCLE -> Circle(5, position)
    FigureType.SMALL_CIRCLE -> Circle(3, position)
    FigureType.SQUARE -> Square(4, position)
    FigureType.SMALL_SQUARE -> Square(2, position)
    FigureType.L_ROOM -> TODO()
    FigureType.LARGE_RECTANGLE -> Rectangle(6, 3, position)
    FigureType.LONG_RECTANGLE -> Rectangle(7, 2, position)
    FigureType.MIDDLE_RECTANGLE -> Rectangle(5, 2, position)
    FigureType.SMALL_RECTANGLE -> Rectangle(4, 2, position)
    FigureType.LARGE_OCTAGON -> TODO()
}
