package com.github.marzr.castles.geometry

infix fun PositionedTile.intersects(other: PositionedTile): Boolean =
    this.toFigure() intersects other.toFigure()

private infix fun Figure.intersects(other: Figure): Boolean {
    return when {
        this is Rectangle && other is Rectangle ->
            xSegment() intersects other.xSegment() && ySegment() intersects other.ySegment()
        else -> false
    }
}

private fun Rectangle.xSegment() = Segment(this.position.x, this.position.x + width)
private fun Rectangle.ySegment() = Segment(this.position.y - height, this.position.y)


private class Segment(val a: Int, val b: Int)

private infix fun Segment.intersects(other: Segment): Boolean =
    (this.a > other.a && this.a < other.b) || (this.a < other.a && this.b > other.a)
