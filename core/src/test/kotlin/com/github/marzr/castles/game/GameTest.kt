package com.github.marzr.castles.game

import org.junit.jupiter.api.Test

class GameTest {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `test init game`() {
        Game(listOf("1", "2", "3", "4"), 1)
        Game(listOf("1", "2", "3"), 2)
        Game(listOf("1", "2"), 3)
    }
}
