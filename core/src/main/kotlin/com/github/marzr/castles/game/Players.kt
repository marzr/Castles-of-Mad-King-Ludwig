package com.github.marzr.castles.game

import java.lang.IllegalStateException
import java.util.*

class Players(private val count: Int) {
    private var current = 0
    private var curentBuyer = 1

    init {
        if (count < 2 || count > 4) throw IllegalStateException("Illegal players count $count")
    }

    val list = Player.PlayerColor.values().take(count).map {
        Player("Player ${it.toString().lowercase(Locale.getDefault())}", it)
    }
    fun builder(): Player = list[current]

    fun currentBuyer() = list[curentBuyer]

    private fun nextBuilder(): Player {
        current+=1
        current%=count
        return list[current]
    }

    fun nextBuyer() {
        curentBuyer +=1
        if (curentBuyer == current)
            curentBuyer += 1
    }

    fun nextTurn() {
        nextBuilder()
        curentBuyer = 0
    }
}
