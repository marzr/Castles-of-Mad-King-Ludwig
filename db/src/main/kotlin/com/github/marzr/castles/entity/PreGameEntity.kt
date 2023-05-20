package com.github.marzr.castles.entity

import com.github.marzr.castles.table.PreGames
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PreGameEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PreGameEntity>(PreGames)
}
