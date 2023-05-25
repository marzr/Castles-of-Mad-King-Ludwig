package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.RoomEntity
import com.github.marzr.castles.table.Players
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class RoomDao {

    fun create(playerId: Long, name: String, x: Int, y: Int, rotation: String) = transaction {
        addLogger(StdOutSqlLogger)
        RoomEntity.new {
            player = EntityID(playerId, Players)
            this.name = name
            this.x = x
            this.y = y
            this.rotation = rotation
        }
    }
}
