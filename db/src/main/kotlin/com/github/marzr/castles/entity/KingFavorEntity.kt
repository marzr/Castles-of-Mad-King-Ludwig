package com.github.marzr.castles.entity

import com.github.marzr.castles.table.KingFavors
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class KingFavorEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<KingFavorEntity>(KingFavors)

    var name by KingFavors.name
    var game by KingFavors.game
}
