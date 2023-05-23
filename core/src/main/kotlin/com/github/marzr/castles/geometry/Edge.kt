package com.github.marzr.castles.geometry

import com.github.marzr.castles.geometry.Position.Rotation.*

sealed interface Edge
data class Horizontal(val y: Int, val x1: Int, val x2: Int) : Edge
data class Vertical(val x: Int, val y1: Int, val y2: Int) : Edge

// координаты берутся по часовой стрелке
data class OcatagonDiagonalEdge(val x1: Int, val y1: Int, val x2: Int, val y2: Int)

fun PositionedTile.top(): Edge = when (val f = this.toFigure()) {
    is Circle -> f.top()
    is Rectangle -> f.top()
    is Octagon -> f.top()
    is LFigure -> TODO("is not supposed to invoke")
}

fun Circle.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x + diameter / 2, x2 = position.x + diameter / 2 + 1)
    R90 -> Vertical(x = position.x + diameter, y1 = position.y - diameter / 2 - 1, y2 = position.y - diameter / 2)
    R180 -> Horizontal(y = position.y - diameter, x1 = position.x + diameter / 2, x2 = position.x + diameter / 2 + 1)
    R270 -> Vertical(x = position.x, y1 = position.y - diameter / 2 - 1, y2 = position.y - diameter / 2)
}

fun Rectangle.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x, x2 = position.x + width)
    R90 -> Vertical(x = position.x + width, y1 = position.y - height, y2 = position.y)
    R180 -> Horizontal(y = position.y - width, x1 = position.x, x2 = position.x + width)
    R270 -> Vertical(x = position.x, y1 = position.y - width, y2 = position.y)
}

fun Octagon.top() = when (position.rotation) {
    R0 -> Horizontal(y = position.y, x1 = position.x + 1, x2 = position.x + width - 1)
    R90 -> Vertical(x = position.x + height, y1 = position.y - height + 1, y2 = position.y - 1)
    R180 -> Horizontal(y = position.y - width, x1 = position.x + width - 1, x2 = position.x + 1)
    R270 -> Vertical(x = position.x, y1 = position.y - width + 1, y2 = position.y - 1)
}

fun PositionedTile.edges() = when (val f = this.toFigure()) {
    is Circle -> f.edges()
    is Octagon -> f.edges()
    is Rectangle -> f.edges()
    is LFigure -> f.edges()
}

fun Circle.edges(): List<Edge> = Position.Rotation.values().map {
    this.copy(position = position.copy(rotation = it)).top()
}

fun Rectangle.edges() =
    if (position.rotation == R0 || position.rotation == R180) {
        listOf(
            Horizontal(y = position.y, x1 = position.x, x2 = position.x + width),
            Vertical(x = position.x, y1 = position.y - height, y2 = position.y),
            Horizontal(y = position.y - height, x1 = position.x, x2 = position.x + width),
            Vertical(x = position.x + width, y1 = position.y - height, y2 = position.y)
        )
    } else {
        listOf(
            Horizontal(y = position.y, x1 = position.x, x2 = position.x + height),
            Vertical(x = position.x, y1 = position.y - width, y2 = position.y),
            Horizontal(y = position.y - width, x1 = position.x, x2 = position.x + height),
            Vertical(x = position.x + height, y1 = position.y - width, y2 = position.y)
        )
    }

fun Octagon.edges() = with(position) {
    if (rotation == R0 || rotation == R180) listOf(
        Horizontal(y = y, x1 = x + 1, x2 = x + width - 1),
        Vertical(x = x, y1 = y - height + 1, y2 = y - 1),
        Horizontal(y = y - height, x1 = x + 1, x2 = x + width - 1),
        Vertical(x = x + width, y1 = y - height + 1, y2 = y - 1)
    )
    else listOf(
        Horizontal(y = y, x1 = x + 1, x2 = x + height - 1),
        Vertical(x = x, y1 = y - width + 1, y2 = y - 1),
        Horizontal(y = y - width, x1 = x + 1, x2 = x + height - 1),
        Vertical(x = x + height, y1 = y - width + 1, y2 = y - 1)
    )
}

fun Octagon.diagonalEdges(): List<OcatagonDiagonalEdge> = with(position) {
    if (rotation == R0 || rotation == R180)
        return listOf(
            OcatagonDiagonalEdge(x, y - 1, x + 1, y),
            OcatagonDiagonalEdge(x + width - 1, y, x + width, y - 1),
            OcatagonDiagonalEdge(x + width, y - height + 1, x + width - 1, y - height),
            OcatagonDiagonalEdge(x + 1, y - height, x, y - height + 1)
        )
    else
        return listOf(
            OcatagonDiagonalEdge(x, y - 1, x + 1, y),
            OcatagonDiagonalEdge(x + height - 1, y, x + height, y - 1),
            OcatagonDiagonalEdge(x + height, y - width + 1, x + height - 1, y - width),
            OcatagonDiagonalEdge(x + 1, y - width, x, y - width + 1)

        )
}

fun LFigure.edges() = when (position.rotation) {
    R0 -> with(position) {
        listOf(
            Horizontal(y, x, x + 2), Vertical(x + 2, y - 2, y), Horizontal(y - 2, x + 2, x + 4),
            Vertical(x + 4, y - 4, y - 2), Horizontal(y - 4, x, x + 4), Vertical(x, y - 4, y)
        )
    }

    R90 -> with(position) {
        listOf(
            Horizontal(y, x, x + 4), Vertical(x + 4, y - 2, y), Horizontal(y - 2, x + 2, x + 4),
            Vertical(x + 2, y - 4, y - 2), Horizontal(y - 4, x, x + 2), Vertical(x, y - 4, y)
        )
    }

    R180 -> with(position) {
        listOf(
            Horizontal(y, x, x + 4), Vertical(x + 4, y - 4, y), Horizontal(y - 4, x + 2, x + 4),
            Vertical(x + 2, y - 4, y - 2), Horizontal(y - 2, x, x + 2), Vertical(x, y - 2, y)
        )
    }

    R270 -> with(position) {
        listOf(
            Horizontal(y, x + 2, x + 4), Vertical(x + 4, y - 4, y), Horizontal(y - 4, x, x + 4),
            Vertical(x, y - 4, y - 2), Horizontal(y - 2, x, x + 2), Vertical(x + 2, y - 2, y)
        )
    }
}
