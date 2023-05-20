package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.RoomPurpose.OUTDOOR
import com.github.marzr.castles.data.rooms.OctagonRoom

fun PositionedTile.hasContactingFence(other: PositionedTile): Boolean {
    if (this.tile.roomPurpose != OUTDOOR)
        return false

    val fence = this.top()

    val fenceSegment = when (fence) {
        is Vertical -> Segment(fence.y1, fence.y2)
        is Horizontal ->  Segment(fence.x1, fence.x2)
    }

    when (fence) {
        is Vertical -> other.edges().filterIsInstance<Vertical>().filter { it.x == fence.x }.map { Segment(it.y1, it.y1) }
        is Horizontal -> other.edges().filterIsInstance<Horizontal>().filter { it.y == fence.y }.map { Segment(it.x1, it.x2) }
    }.forEach {
        if (it intersects fenceSegment)
            return true
    }
    if (this.tile is OctagonRoom && other.tile is OctagonRoom)
        TODO()

    return false
}
