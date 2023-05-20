package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.PreGameEntity
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class PreGameDao {
    fun create(): PreGameEntity = transaction {
        addLogger(StdOutSqlLogger)
        PreGameEntity.new {}
    }
}
