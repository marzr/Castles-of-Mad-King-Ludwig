package com.github.marzr.castles.geometry

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HalfPointTest {

    @Test
    fun testDistance() {
        assertEquals(2.0, HalfPoint(0, 0).distance(HalfPoint(4, 0)) , 0.001)
    }
}
