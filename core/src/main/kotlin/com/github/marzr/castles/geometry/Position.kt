package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.Foyer

/**
 * @param[x] x position of left top corner of the tile
 * @param[y] y position of left top corner of the tile
 * @param[rotation] rotation of the tile
 *
 * @note For non-rectangle figures ([FigureType.L_ROOM], [FigureType.BIG_CIRCLE], [FigureType.SMALL_CIRCLE],
 * [FigureType.LARGE_OCTAGON], [Foyer]) left top corner is a left top corner of circumscribed rectangle.
 */
data class Position(val x: Int, val y: Int, val rotation: Rotation = Rotation.R0) {
    enum class Rotation {
        R0, R90, R180, R270
    }
}
