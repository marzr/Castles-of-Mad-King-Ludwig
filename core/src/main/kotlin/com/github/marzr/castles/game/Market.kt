package com.github.marzr.castles.game

import com.github.marzr.castles.data.rooms.RoomTile

data class Market(private val tiles: MutableList<RoomTile> = mutableListOf()) {

    fun addTiles(tiles: List<RoomTile>) {
        this.tiles.addAll(tiles)
    }
}
