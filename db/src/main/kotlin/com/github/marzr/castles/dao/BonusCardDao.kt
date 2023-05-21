package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.BonusCardEntity
import com.github.marzr.castles.table.BonusCards
import com.github.marzr.castles.table.Players
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class BonusCardDao {

    fun addBonus(cardId: String, playerId: Long, toChoose: Boolean) = transaction {
        addLogger(StdOutSqlLogger)
        BonusCardEntity.new {
            name = cardId
            player = EntityID(playerId, Players)
            this.toChoose = toChoose
        }
    }

    fun getBonuses(playerId: Long): List<BonusCardEntity> = transaction {
        addLogger(StdOutSqlLogger)
        BonusCardEntity.find(BonusCards.player eq playerId).toList()
    }

    fun removeBonusesToChoose(playerId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        BonusCardEntity.find(BonusCards.player eq playerId and BonusCards.toChoose).forEach {
            it.delete()
        }
    }
}
