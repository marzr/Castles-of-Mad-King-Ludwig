package com.github.marzr.castles.service

import com.github.marzr.castles.dao.BonusCardDao
import com.github.marzr.castles.dao.PlayerDao
import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.game.Player

class PlayerDbService(
    private val playerDao: PlayerDao,
    private val bonusCardDao: BonusCardDao
) {
    fun create(gameId: Long, player: Player): Long {
        val playerId = playerDao.create(gameId, player.name, player.money, player.color.name).id.value
        player.bonusesToChoose.forEach {
            bonusCardDao.addBonus(it.id, playerId, toChoose = true)
        }
        return playerId
    }

    fun load(name: String, gameId: Long): Player {
        val entity = playerDao.get(gameId, name)
        val bonusCards = bonusCardDao.getBonuses(entity.id.value).map {
            BonusCard.bonusCardById[it.name]!!
        }.toMutableList()
        return Player(name, enumValueOf(entity.color), bonuses = bonusCards)
    }

    fun plusMoney(name: String, gameId: Long, amount: Int): Int =
        playerDao.plusMoney(gameId, name, amount)

    fun minusMoney(name: String, gameId: Long, amount: Int): Int =
        playerDao.minusMoney(gameId, name, amount)
}
