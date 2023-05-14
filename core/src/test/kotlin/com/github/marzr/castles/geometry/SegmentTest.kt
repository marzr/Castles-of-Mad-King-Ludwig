package com.github.marzr.castles.geometry

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SegmentTest {
    @Test
    fun `should intersect`() {
        assertTrue(Segment(1, 3) intersects Segment(2, 4))
        assertTrue(Segment(2, 4) intersects Segment(1, 3))
        assertTrue(Segment(1, 4) intersects Segment(2, 3))
        assertTrue(Segment(2, 3) intersects Segment(1, 4))
        assertTrue(Segment(1, 2) intersects Segment(1, 2))
        assertTrue(Segment(1, 2) intersects Segment(1, 3))
        assertTrue(Segment(1, 3) intersects Segment(1, 2))
    }

    @Test
    fun `should not intersect`() {
        assertFalse(Segment(1, 3) intersects Segment(4, 5))
        assertFalse(Segment(3, 4) intersects Segment(1, 2))
    }
}
