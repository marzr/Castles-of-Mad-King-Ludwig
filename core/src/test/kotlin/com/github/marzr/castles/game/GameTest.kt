package com.github.marzr.castles.game

import org.junit.jupiter.api.Test

class GameTest {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun `test init game`() {
        Game(4,1)
        Game(3, 2)
        Game(2, 3)
    }
}
