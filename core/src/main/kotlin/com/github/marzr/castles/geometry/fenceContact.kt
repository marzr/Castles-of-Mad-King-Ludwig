package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.RoomType.OUTDOOR
import com.github.marzr.castles.data.rooms.OctagonRoom
import com.github.marzr.castles.geometry.Position.Rotation.*

fun PositionedTile.hasContactingFence(other: PositionedTile): Boolean {
    if (this.tile.roomType != OUTDOOR)
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

private fun List<Edge>.flat() = this.flatMap {
    when (it) {
        is Two -> listOf(it.edge1, it.edge2)
        else -> listOf(it)
    }
}

private sealed interface Edge
private data class Horizontal(val y: Int, val x1: Int, val x2: Int) : Edge
private data class Vertical(val x: Int, val y1: Int, val y2: Int) : Edge
private data class Two(val edge1: Edge, val edge2: Edge) : Edge


private fun PositionedTile.top(): Edge = when (val f = this.toFigure()) {
    is Circle -> f.top()
    is Rectangle -> f.top()
    is Octagon -> f.top()

}

private fun Circle.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x + diameter / 2, x2 = position.x + diameter / 2 + 1)
    R90 -> Vertical(x = position.x + diameter, y1 = position.y - diameter / 2, y2 = position.y - diameter / 2 - 1)
    R180 -> Horizontal(y = position.y - diameter, x1 = position.x + diameter / 2 + 1, x2 = position.x + diameter / 2)
    R270 -> Vertical(x = position.x, y1 = position.y - diameter / 2 - 1, y2 = position.y - diameter / 2)
}

private fun Rectangle.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x, x2 = position.x + width)
    R90 -> Vertical(x = position.x + width, y1 = position.y, y2 = position.y - height)
    R180 -> Horizontal(y = position.y - width, x1 = position.x + width, x2 = position.x)
    R270 -> Vertical(x = position.x, y1 = position.y - height, y2 = position.y)
}

private fun Octagon.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x + 1, x2 = position.x + width - 1)
    R90 -> Vertical(x = position.x + width, y1 = position.y - 1, y2 = position.y - height + 1)
    R180 -> Horizontal(y = position.y - width, x1 = position.x + width - 1, x2 = position.x + 1)
    R270 -> Vertical(x = position.x, y1 = position.y - height + 1, y2 = position.y - 1)
}

private fun PositionedTile.edges() = when(val f = this.toFigure()) {
    is Circle -> f.edges()
    else -> TODO()
}

private fun Circle.edges() = Position.Rotation.values().map {
    this.copy(position = position.copy(rotation = it)).top()
}
