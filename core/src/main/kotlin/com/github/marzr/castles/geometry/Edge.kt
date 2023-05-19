package com.github.marzr.castles.geometry

sealed interface Edge
data class Horizontal(val y: Int, val x1: Int, val x2: Int) : Edge
data class Vertical(val x: Int, val y1: Int, val y2: Int) : Edge

fun PositionedTile.top(): Edge = when (val f = this.toFigure()) {
    is Circle -> f.top()
    is Rectangle -> f.top()
    is Octagon -> f.top()
}

fun Circle.top() = when (position.rotation) {
    Position.Rotation.R0 -> Horizontal(
        y = position.y,
        x1 = position.x + diameter / 2,
        x2 = position.x + diameter / 2 + 1
    )

    Position.Rotation.R90 -> Vertical(
        x = position.x + diameter,
        y1 = position.y - diameter / 2 - 1,
        y2 = position.y - diameter / 2
    )

    Position.Rotation.R180 -> Horizontal(
        y = position.y - diameter,
        x1 = position.x + diameter / 2,
        x2 = position.x + diameter / 2 + 1
    )

    Position.Rotation.R270 -> Vertical(
        x = position.x,
        y1 = position.y - diameter / 2 - 1,
        y2 = position.y - diameter / 2
    )
}

fun Rectangle.top() = when (position.rotation) {
    Position.Rotation.R0 -> Horizontal(y = position.y, x1 = position.x, x2 = position.x + width)
    Position.Rotation.R90 -> Vertical(x = position.x + width, y1 = position.y - height, y2 = position.y)
    Position.Rotation.R180 -> Horizontal(y = position.y - width, x1 = position.x, x2 = position.x + width)
    Position.Rotation.R270 -> Vertical(x = position.x, y1 = position.y - width, y2 = position.y)
}

fun Octagon.top() = when (position.rotation) {
    Position.Rotation.R0 -> Horizontal(y = position.y, x1 = position.x + 1, x2 = position.x + width - 1)
    Position.Rotation.R90 -> Vertical(x = position.x + width, y1 = position.y - 1, y2 = position.y - height + 1)
    Position.Rotation.R180 -> Horizontal(y = position.y - width, x1 = position.x + width - 1, x2 = position.x + 1)
    Position.Rotation.R270 -> Vertical(x = position.x, y1 = position.y - width + 1, y2 = position.y - 1)
}

fun PositionedTile.edges() = when (val f = this.toFigure()) {
    is Circle -> f.edges()
    is Octagon -> f.edges()
    is Rectangle -> f.edges()
}

fun Circle.edges() = Position.Rotation.values().map {
    this.copy(position = position.copy(rotation = it)).top()
}

fun Rectangle.edges() =
    if (position.rotation == Position.Rotation.R0 || position.rotation == Position.Rotation.R180) {
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

fun Octagon.edges() =
    if (position.rotation == Position.Rotation.R0 || position.rotation == Position.Rotation.R180) {
        listOf(
            Horizontal(y = position.y, x1 = position.x + 1, x2 = position.x + width - 1),
            Vertical(x = position.x, y1 = position.y - height + 1, y2 = position.y - 1),
            Horizontal(y = position.y - height, x1 = position.x + 1, x2 = position.x + width - 1),
            Vertical(x = position.x + width, y1 = position.y - height + 1, y2 = position.y - 1)
        )
    } else {
        listOf(
            Horizontal(y = position.y, x1 = position.x + 1, x2 = position.x + height - 1),
            Vertical(x = position.x, y1 = position.y - width + 1, y2 = position.y - 1),
            Horizontal(y = position.y - width, x1 = position.x + 1, x2 = position.x + height - 1),
            Vertical(x = position.x + height, y1 = position.y - width + 1, y2 = position.y - 1)
        )
    }
