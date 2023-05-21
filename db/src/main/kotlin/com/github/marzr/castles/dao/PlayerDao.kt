package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.PlayerEntity
import com.github.marzr.castles.table.Games
import com.github.marzr.castles.table.Players
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class PlayerDao {

    fun create(name: String, money: Int, gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        PlayerEntity.new {
            this.name = name
            this.money = money
            game = EntityID(gameId, Games)
        }
    }

    fun get(name: String, gameId: Long): PlayerEntity = transaction {
        addLogger(StdOutSqlLogger)
        PlayerEntity.find(Players.game eq gameId and (Players.name eq name)).first()
    }

    fun plusMoney(name: String, gameId: Long, amount: Int) = transaction {
        addLogger(StdOutSqlLogger)
        val player = PlayerEntity.find(Players.game eq gameId and (Players.name eq name))
            .first()
        player.money += amount
        player.money
    }

    fun minusMoney(name: String, gameId: Long, amount: Int): Int = transaction {
        addLogger(StdOutSqlLogger)
        val player = PlayerEntity.find(Players.game eq gameId and (Players.name eq name))
            .first()
        player.money -= amount
        player.money
    }
}
