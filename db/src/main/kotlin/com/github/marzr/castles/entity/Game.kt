package com.github.marzr.castles.entity

import com.github.marzr.castles.table.Games
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Game(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Game>(Games)

    var playersCount by Games.playersCount
}
