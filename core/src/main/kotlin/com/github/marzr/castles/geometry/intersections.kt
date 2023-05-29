package com.github.marzr.castles.geometry

import com.github.marzr.castles.geometry.Position.Rotation.*

infix fun PositionedTile.intersects(other: PositionedTile): Boolean =
    this.toFigure() intersects other.toFigure()

private infix fun Figure.intersects(other: Figure): Boolean {
    return when {
        this is Rectangle && other is Rectangle ->
            xSegment() intersects other.xSegment() && ySegment() intersects other.ySegment()
        else -> false
    }
}

private fun Rectangle.xSegment() = if (position.rotation == R0 || position.rotation == R180)
    Segment(position.x, position.x + width)
else
    Segment(position.x, position.x + height)
private fun Rectangle.ySegment() = if (position.rotation == R0 || position.rotation == R180)
    Segment(this.position.y - height, this.position.y)
else
    Segment(this.position.y - width, this.position.y)
