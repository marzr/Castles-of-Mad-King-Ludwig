package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.JoinedUserEntity
import com.github.marzr.castles.table.JoinedUsers
import com.github.marzr.castles.table.PreGames
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class JoinedUsersDao {
    fun joinGame(name: String, preGameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        JoinedUserEntity.new {
            this.name = name
            this.preGame = EntityID(preGameId, PreGames)
        }
    }

    fun getJoinedUsers(preGameId: Long) = transaction {
        JoinedUserEntity.find(JoinedUsers.preGame eq preGameId).map {
            it.name
        }.toList()
    }
}
