package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.FigureType

interface OctagonRoom : RoomTile {
    override val figureType
        get() = FigureType.LARGE_OCTAGON
}

interface SmallSquareRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.SMALL_SQUARE
}

interface SquareRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.SQUARE
}

interface SmallRectangleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.SMALL_RECTANGLE
}

interface BigCircleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.BIG_CIRCLE
}

interface LongRectangleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.LONG_RECTANGLE
}

interface SmallCircleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.SMALL_CIRCLE
}

interface MiddleRectangleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.MIDDLE_RECTANGLE
}

interface LargeRectangleRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.LARGE_RECTANGLE
}

interface LRoom : RoomTile {
    override val figureType: FigureType
        get() = FigureType.L_ROOM
}
