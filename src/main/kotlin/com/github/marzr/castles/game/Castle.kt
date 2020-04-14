package com.github.marzr.castles.game

import com.github.marzr.castles.data.Tile
import com.github.marzr.castles.geometry.Position

interface Castle {

    fun addTile(tile: Tile, position: Position): TileAddResult
}

sealed class TileAddResult {
    class Success(val castle: Castle, val reward: InstantReward) : TileAddResult()
    object Fail : TileAddResult()
}
