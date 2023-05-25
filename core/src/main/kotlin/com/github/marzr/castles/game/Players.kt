package com.github.marzr.castles.game

import java.lang.IllegalStateException
import java.util.*

class Players(users: List<String>) {
    private var current = 0
    private var curentBuyer = 1

    init {
        if (users.size < 2 || users.size > 4)
            throw IllegalStateException("Illegal players count ${users.size}")
    }

    companion object {
        const val INITIAL_MONEY_AMOUNT = 15000
    }

    var list: List<Player> = emptyList()

    //TODO remove
    private val map = list.mapIndexed { i, player -> ("p${i + 1}" to player) }.toMap()
    private val mapById = list.associate { (it.id to it) }


    fun get(userName: String) = map[userName]!!

    fun get(id: Long) = mapById[id]!!

    fun builder(): Player = list[current]

    fun currentBuyer() = list[curentBuyer]

    private fun nextBuilder(): Player {
        current += 1
        current %= list.size
        return list[current]
    }

    fun nextBuyer() {
        curentBuyer += 1
        if (curentBuyer == current)
            curentBuyer += 1
    }

    fun nextTurn() {
        nextBuilder()
        curentBuyer = 0
    }
}
