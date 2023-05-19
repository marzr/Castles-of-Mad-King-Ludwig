package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.RoomPurpose.OUTDOOR
import com.github.marzr.castles.data.rooms.OctagonRoom

fun PositionedTile.hasContactingFence(other: PositionedTile): Boolean {
    if (this.tile.roomPurpose != OUTDOOR)
        return false

    val fence = this.top()

    if (fence is Vertical) {
        other.edges().flat().filterIsInstance<Vertical>()
    }

    if (fence is Horizontal) {
        other.edges().flat().filterIsInstance<Vertical>()
    }
    if (this.tile is OctagonRoom && other.tile is OctagonRoom)
        TODO()

    return false
}
