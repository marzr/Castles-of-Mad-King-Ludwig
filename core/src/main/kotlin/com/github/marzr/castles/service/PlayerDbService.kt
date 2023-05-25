package com.github.marzr.castles.service

import com.github.marzr.castles.dao.BonusCardDao
import com.github.marzr.castles.dao.PlayerDao
import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.game.Player

class PlayerDbService(
    private val playerDao: PlayerDao,
    private val bonusCardDao: BonusCardDao
) {
    fun create(gameId: Long, name: String, money: Int, color: Player.PlayerColor): Long =
        playerDao.create(gameId, name, money, color.name).id.value


    fun load(name: String, gameId: Long): Player {
        val entity = playerDao.get(gameId, name)
        val bonusCards = bonusCardDao.getBonuses(entity.id.value).map {
            BonusCard.bonusCardById[it.name]!!
        }.toMutableList()
        return Player(entity.id.value, name, entity.money, enumValueOf(entity.color), bonuses = bonusCards)
    }

    fun plusMoney(name: String, gameId: Long, amount: Int): Int =
        playerDao.plusMoney(gameId, name, amount)

    fun minusMoney(name: String, gameId: Long, amount: Int): Int =
        playerDao.minusMoney(gameId, name, amount)
}
