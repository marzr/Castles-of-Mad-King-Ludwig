package com.github.marzr.castles.entity

import com.github.marzr.castles.table.BonusCards
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BonusCardEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BonusCardEntity>(BonusCards)

    var name by BonusCards.name
    var player by BonusCards.player
    var toChoose by BonusCards.toChoose
}
