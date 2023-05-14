package com.github.marzr.castles.game

import org.junit.jupiter.api.Test

class GameTest {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `test init game`() {
        Game(4)
        Game(3)
        Game(2)
    }
}
