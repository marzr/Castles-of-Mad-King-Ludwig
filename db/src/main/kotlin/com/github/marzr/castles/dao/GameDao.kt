package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.GameEntity
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class GameDao {

    fun create(): GameEntity = transaction {
        addLogger(StdOutSqlLogger)
        GameEntity.new {}
    }
}
