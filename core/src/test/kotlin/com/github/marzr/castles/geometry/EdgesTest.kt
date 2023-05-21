package com.github.marzr.castles.geometry

import com.github.marzr.castles.geometry.Position.Rotation.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EdgesTest {

    @Test
    fun `test circle`() {
        val circle = Circle(3, Position(1, 1, R90))
        val expectedEdges = setOf(
            Horizontal(1, 2, 3),
            Vertical(1, -1, 0),
            Horizontal(-2, 2, 3),
            Vertical(4, -1, 0)
        )
        assertEquals(expectedEdges, circle.edges().toSet())
    }

    @Test
    fun `test rectangle vertical`() {
        val rectangle = Rectangle(4, 2, Position(1, 4, R270))
        val expectedEdges = setOf(
            Horizontal(4, 1, 3),
            Vertical(1, 0, 4),
            Horizontal(0, 1, 3),
            Vertical(3, 0, 4)
        )
        assertEquals(expectedEdges, rectangle.edges().toSet())
    }

    @Test
    fun `test rectangle horizontal`() {
        val rectangle = Rectangle(4, 2, Position(1, 4, R270))
        val expectedEdges = setOf(
            Horizontal(4, 1, 3),
            Vertical(1, 0, 4),
            Horizontal(0, 1, 3),
            Vertical(3, 0, 4)
        )
        assertEquals(expectedEdges, rectangle.edges().toSet())
    }

    @Test
    fun `test octagon horizontal`() {
        val octagon = Octagon(7, 4, Position(1, 5, R0))
        val expectedEdges = setOf(
            Horizontal(5, 2, 7),
            Vertical(1, 2, 4),
            Horizontal(1, 2, 7),
            Vertical(8, 2, 4)
        )
        assertEquals(expectedEdges, octagon.edges().toSet())
    }

    @Test
    fun `test octagon vertical`() {
        val octagon = Octagon(7, 4, Position(1, 4, R90))
        val expectedEdges = setOf(
            Horizontal(4, 2, 4),
            Vertical(1, -2, 3),
            Horizontal(-3, 2, 4),
            Vertical(5, -2, 3)
        )
        assertEquals(expectedEdges, octagon.edges().toSet())
    }

    @Test
    fun `test L figure R90`() {
        val lFigure = LFigure(Position(-1, 2, R90))
        val expectedEdges = setOf(
            Horizontal(2, -1, 3),
            Horizontal(0, 1, 3),
            Horizontal(-2, -1, 1),
            Vertical(-1, -2, 2),
            Vertical(1, -2, 0),
            Vertical(3, 0, 2)
        )
        assertEquals(expectedEdges, lFigure.edges().toSet())
    }

    @Test
    fun `test L figure R180`() {
        val lFigure = LFigure(Position(-2, 1, R180))
        val expectedEdges = setOf(
            Horizontal(1, -2, 2),
            Horizontal(-1, -2, 0),
            Horizontal(-3, 0, 2),
            Vertical(-2, -1, 1),
            Vertical(0, -3, -1),
            Vertical(2, -3, 1)
        )
        assertEquals(expectedEdges, lFigure.edges().toSet())
    }

    @Test
    fun `test octagon diagonal edges R90`() {
        val octagon = Octagon(4, 3, Position(-1, 3, R90))
        val expectedEdges = setOf(
            OcatagonDiagonalEdge(-1, 2, 0, 3),
            OcatagonDiagonalEdge(1, 3, 2, 2),
            OcatagonDiagonalEdge(2, 0, 1, -1),
            OcatagonDiagonalEdge(0, -1, -1, 0)
        )
        assertEquals(expectedEdges, octagon.diagonalEdges().toSet())
    }

    @Test
    fun `test octagon diagonal edges R0`() {
        val octagon = Octagon(5, 3, Position(-1, 4, R0))
        val expectedEdges = setOf(
            OcatagonDiagonalEdge(-1, 3, 0, 4),
            OcatagonDiagonalEdge(3, 4, 4, 3),
            OcatagonDiagonalEdge(4, 2, 3, 1),
            OcatagonDiagonalEdge(0, 1, -1, 2)
        )
        assertEquals(expectedEdges, octagon.diagonalEdges().toSet())
    }
}