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

    fun create(gameId: Long, name: String, money: Int, color: String) = transaction {
        addLogger(StdOutSqlLogger)
        PlayerEntity.new {
            this.name = name
            this.money = money
            game = EntityID(gameId, Games)
            this.color = color
        }
    }

    fun get(gameId: Long, name: String): PlayerEntity = transaction {
        addLogger(StdOutSqlLogger)
        PlayerEntity.find(Players.game eq gameId and (Players.name eq name)).first()
    }

    fun plusMoney(gameId: Long, name: String, amount: Int) = transaction {
        addLogger(StdOutSqlLogger)
        val player = PlayerEntity.find(Players.game eq gameId and (Players.name eq name))
            .first()
        player.money += amount
        player.money
    }

    fun minusMoney(gameId: Long, name: String, amount: Int): Int = transaction {
        addLogger(StdOutSqlLogger)
        val player = PlayerEntity.find(Players.game eq gameId and (Players.name eq name))
            .first()
        player.money -= amount
        player.money
    }
}
