package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.KingFavorEntity
import com.github.marzr.castles.table.Games
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class KingFavorDao {

    fun create(gameId: Long, id: String) = transaction {
        addLogger(StdOutSqlLogger)
        KingFavorEntity.new {
            name = id
            game = EntityID(gameId, Games)
        }
    }
}
