package com.github.marzr.castles.entity

import com.github.marzr.castles.table.JoinedUsers
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class JoinedUserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<JoinedUserEntity>(JoinedUsers)

    var name by JoinedUsers.name
    var preGame by JoinedUsers.preGame
}
