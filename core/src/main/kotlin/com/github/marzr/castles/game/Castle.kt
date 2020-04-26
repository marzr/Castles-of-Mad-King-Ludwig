package com.github.marzr.castles.game

import com.github.marzr.castles.geometry.HalfPoint
import com.github.marzr.castles.geometry.PositionedTile
import com.github.marzr.castles.geometry.toFigure

interface ICastle {

    fun addTile(positionedTile: PositionedTile): TileAddResult
}

sealed class TileAddResult {
    data class Success(val reward: InstantReward) : TileAddResult()
    object Fail : TileAddResult()
}

class Castle : ICastle {
    private val tiles = mutableListOf<PositionedTile>()
    private val occupiedHalfPoints = mutableSetOf<HalfPoint>()

    override fun addTile(positionedTile: PositionedTile): TileAddResult {
        val innerPoints = (positionedTile.toFigure().setOfInnerPoints())
        return if (occupiedHalfPoints.all { !innerPoints.contains(it) }) {
            tiles.add(positionedTile)
            occupiedHalfPoints.addAll(positionedTile.toFigure().setOfPoints())

            TileAddResult.Success(InstantReward(0))
        } else
            TileAddResult.Fail
    }
}
