package com.github.marzr.castles.geometry

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EdgesTest {

    @Test
    fun `test circle`() {
        val circle = Circle(3, Position(1, 1, Position.Rotation.R90))
        val expectedEdges = setOf(
            Horizontal(1, 2,3),
            Vertical(1, -1, 0),
            Horizontal(-2, 2, 3),
            Vertical(4, -1, 0)
        )
        assertEquals(expectedEdges, circle.edges().toSet())
    }

    @Test
    fun `test rectangle vertical`() {
        val rectangle = Rectangle(4, 2, Position(1, 4, Position.Rotation.R270))
        val expectedEdges = setOf(
            Horizontal(4, 1,3),
            Vertical(1, 0, 4),
            Horizontal(0, 1, 3),
            Vertical(3, 0, 4)
        )
        assertEquals(expectedEdges, rectangle.edges().toSet())
    }

    @Test
    fun `test rectangle horizontal`() {
        val rectangle = Rectangle(4, 2, Position(1, 4, Position.Rotation.R270))
        val expectedEdges = setOf(
            Horizontal(4, 1,3),
            Vertical(1, 0, 4),
            Horizontal(0, 1, 3),
            Vertical(3, 0, 4)
        )
        assertEquals(expectedEdges, rectangle.edges().toSet())
    }

    @Test
    fun `test octagon horizontal`() {
        val octagon = Octagon(7, 4, Position(1, 5, Position.Rotation.R0))
        val expectedEdges = setOf(
            Horizontal(5, 2,7),
            Vertical(1, 2, 4),
            Horizontal(1, 2, 7),
            Vertical(8, 2, 4)
        )
        assertEquals(expectedEdges, octagon.edges().toSet())
    }

    @Test
    fun `test octagon vertical`() {
        val octagon = Octagon(7, 4, Position(1, 4, Position.Rotation.R90))
        val expectedEdges = setOf(
            Horizontal(4, 2,4),
            Vertical(1, -2, 3),
            Horizontal(-3, 2, 4),
            Vertical(5, -2, 3)
        )
        assertEquals(expectedEdges, octagon.edges().toSet())
    }

}