package com.github.marzr.castles.game

import com.github.marzr.castles.data.Tile
import com.github.marzr.castles.geometry.TilePosition

interface Castle {

    fun addTile(tile: Tile, position: TilePosition): Pair<Castle, InstantReward>
}