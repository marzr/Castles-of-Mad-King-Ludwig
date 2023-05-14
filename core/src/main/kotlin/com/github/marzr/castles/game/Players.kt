package com.github.marzr.castles.game

import java.lang.IllegalStateException
import java.util.*

class Players(private val count: Int) {
    private var current = 0

    init {
        if (count < 2 || count > 4) throw IllegalStateException("Illegal players count $count")
    }

    val list = Player.PlayerColor.values().take(count).map {
        Player("Player ${it.toString().lowercase(Locale.getDefault())}", it)
    }
    fun current(): Player = list[current]

    fun next(): Player {
        current+=1
        current%=count
        return list[current]
    }
}
