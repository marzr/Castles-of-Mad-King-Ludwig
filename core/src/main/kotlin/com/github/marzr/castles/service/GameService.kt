package com.github.marzr.castles.service

import com.github.marzr.castles.game.Game
import com.github.marzr.castles.game.Player
import java.util.concurrent.atomic.AtomicLong

private const val MONEY_TO_RECEIVE_WHEN_PASS_TURN = 5000

@OptIn(ExperimentalStdlibApi::class)
class GameService {

    private val games: MutableMap<Long, Game> = mutableMapOf()
    private val idGenerator: AtomicLong = AtomicLong(0)

    fun createGame(playersCount: Int): Game {
        val id = idGenerator.getAndIncrement()
        return Game(playersCount, id).also { games[id] = it }
    }

    fun getGame(id: Long): Game? = games[id]

    fun passTurnAndReceiveMoney(player: Player): Player {
        player.money = player.money + MONEY_TO_RECEIVE_WHEN_PASS_TURN
        return player
    }
}
