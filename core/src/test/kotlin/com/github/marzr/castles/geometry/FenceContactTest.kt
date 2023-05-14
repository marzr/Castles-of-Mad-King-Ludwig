package com.github.marzr.castles.geometry

import com.github.marzr.castles.data.rooms.roomsByTitle
import org.junit.jupiter.api.Test

class FenceContactTest {

    @Test
    fun test() {
        val garden = PositionedTile(roomsByTitle["Сад тыкв"]!!, Position(0, 4, Position.Rotation.R0))
    }
}
