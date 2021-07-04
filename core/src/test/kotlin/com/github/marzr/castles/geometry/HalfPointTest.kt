package com.github.marzr.castles.geometry

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HalfPointTest {

    @Test
    fun testDistance() {
        val dist = HalfPoint(0, 0).distance(HalfPoint(4, 0))
        assertEquals(2.0, dist, 0.001)
    }
}
