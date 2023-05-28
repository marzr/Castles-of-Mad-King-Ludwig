package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.RoomPurpose.OUTDOOR

fun PositionedTile.hasContactingFence(other: PositionedTile): Boolean {
    if (this.tile.roomPurpose != OUTDOOR)
        return false

    val fence = this.top()

    val fenceSegment = when (fence) {
        is Vertical -> Segment(fence.y1, fence.y2)
        is Horizontal -> Segment(fence.x1, fence.x2)
    }

    when (fence) {
        is Vertical -> other.edges().filterIsInstance<Vertical>().filter { it.x == fence.x }
            .map { Segment(it.y1, it.y2) }

        is Horizontal -> other.edges().filterIsInstance<Horizontal>().filter { it.y == fence.y }
            .map { Segment(it.x1, it.x2) }
    }.forEach {
        if (it intersects fenceSegment)
            return true
    }

    val thisFigure = this.toFigure()
    val otherFigure = other.toFigure()
    if (thisFigure is Octagon && otherFigure is Octagon) {
        val edge = thisFigure.diagonalEdges().filter {
            when (fence) {
                is Vertical -> it.x1 == fence.x || it.x2 == fence.x
                is Horizontal -> it.y1 == fence.y || it.y2 == fence.y
            }
        }.find { thisEdge ->
            otherFigure.diagonalEdges().find { otherEdge ->
                thisEdge == otherEdge || thisEdge == otherEdge.copy(otherEdge.x2, otherEdge.y2, otherEdge.x1, otherEdge.y1)
            } != null
        }
        if (edge != null)
            return true
    }
    return false
}
