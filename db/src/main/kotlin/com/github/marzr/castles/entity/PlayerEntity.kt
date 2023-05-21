package com.github.marzr.castles.entity

import com.github.marzr.castles.table.Players
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PlayerEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PlayerEntity>(Players)

    var name by Players.name
    var game by Players.game
    var money by Players.money
    var color by Players.color
}
