package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.Stairs
import com.github.marzr.castles.data.rooms.RoomTile

interface Figure {

    fun setOfPoints(): Collection<HalfPoint>

    fun setOfInnerPoints(): Collection<HalfPoint>
}

class Square(side: Int, position: FigurePosition) : Rectangle(side, side, position)

open class Octagon(
        private val width: Int,
        private val height: Int,
        private val position: FigurePosition
) : Figure {

    override fun setOfInnerPoints(): Collection<HalfPoint> =
            (2 * position.x + 1 until 2 * position.x + 2 * width).flatMap { i ->
                (2 * position.y + 1 until 2 * position.y + 2 * height).map { j ->
                    HalfPoint(i, j)  //the integral points correspond to points of half the coordinates
                    //hence 'position' is squared
                }
            }


    override fun setOfPoints(): Collection<HalfPoint> =
            (2 * position.x..2 * position.x + 2 * width).flatMap { i ->
                (2 * position.y..2 * position.y + 2 * height).mapNotNull { j ->
                    if (
                            (i == 2 * position.x || i == 2 * position.x + 2 * width) &&
                            (j == 2 * position.y || j == 2 * position.x + 2 * height)
                    ) null else HalfPoint(i, j) //the integral points correspond to points of half the coordinates
                    //hence 'position' is squared
                }
            }

}

open class Rectangle(val width: Int, val height: Int, val position: FigurePosition) : Figure {

    override fun setOfInnerPoints(): Collection<HalfPoint> =
            (2 * position.x + 1 until 2 * position.x + 2 * width).flatMap { i ->
                (2 * position.y + 1 until 2 * position.y + 2 * height).map { j ->
                    HalfPoint(i, j)  //the integral points correspond to points of half the coordinates
                    //hence 'position' is squared
                }
            }.toSet()


    override fun setOfPoints(): Collection<HalfPoint> =
            (2 * position.x..2 * position.x + 2 * width).flatMap { i ->
                (2 * position.y..2 * position.y + 2 * height).map { j ->
                    HalfPoint(i, j)  //the integral points correspond to points of half the coordinates
                    //hence 'position' is squared
                }
            }

}

class Circle(
        private val diameter: Int,
        private val position: FigurePosition
) : Figure {
    override fun setOfPoints(): Collection<HalfPoint> {
        val centreHalfPoint = HalfPoint(2 * position.x + diameter, 2 * position.y + diameter)

        return (2 * position.x..2 * position.x + 2 * diameter).flatMap { i ->
            (2 * position.y..2 * position.y + 2 * diameter).mapNotNull { j ->
                val point = HalfPoint(i, j)
                if (centreHalfPoint.distance(point) <= diameter)
                    point
                else null
            }
        }
    }


    override fun setOfInnerPoints(): Collection<HalfPoint> {
        val centreHalfPoint = HalfPoint(2 * position.x + diameter, 2 * position.y + diameter)

        return (2 * position.x..2 * position.x + 2 * diameter).flatMap { i ->
            (2 * position.y..2 * position.y + 2 * diameter).mapNotNull { j ->
                val point = HalfPoint(i, j)
                if (centreHalfPoint.distance(point) * 2 < diameter)
                    point else null
            }
        }
    }
}

fun PositionedTile.toFigure(): Figure = when (val tile = this.tile) {
    is RoomTile -> tile.toFigure(FigurePosition(position.x, position.y))
    is Foyer -> Octagon(3, 3, FigurePosition(position.x, position.y))
    is Stairs -> Rectangle(3, 1, FigurePosition(position.x, position.y))
    else -> TODO()
}

fun RoomTile.toFigure(position: FigurePosition): Figure = when (this.figureType) {
    FigureType.BIG_CIRCLE -> Circle(5, position)
    FigureType.SMALL_CIRCLE -> Circle(3, position)
    FigureType.SQUARE -> Square(4, position)
    FigureType.SMALL_SQUARE -> Square(2, position)
    FigureType.LARGE_OCTAGON -> Octagon(7, 4, position)
    FigureType.L_ROOM -> TODO()
    FigureType.LARGE_RECTANGLE -> Rectangle(6, 3, position)
    FigureType.LONG_RECTANGLE -> Rectangle(7, 2, position)
    FigureType.MIDDLE_RECTANGLE -> Rectangle(5, 2, position)
    FigureType.SMALL_RECTANGLE -> Rectangle(4, 2, position)
}
