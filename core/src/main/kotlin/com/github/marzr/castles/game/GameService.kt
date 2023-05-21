package com.github.marzr.castles.game

import java.util.concurrent.atomic.AtomicLong

@OptIn(ExperimentalStdlibApi::class)
class GameService {

    private val games: MutableMap<Long, Game> = mutableMapOf()
    private val idGenerator: AtomicLong = AtomicLong(0)

    fun createGame(playersCount: Int): Game {
        val id = idGenerator.getAndIncrement()
        return Game(playersCount, id).also { games[id] = it }
    }

    fun getGame(id: Long): Game? = games[id]

    fun getPlayerMoney(player: Player): Player {
        player.money = player.money + 5000
        return player
    }
}
