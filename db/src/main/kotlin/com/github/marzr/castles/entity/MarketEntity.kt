package com.github.marzr.castles.entity

import com.github.marzr.castles.table.Markets
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class MarketEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<MarketEntity>(Markets)

    var game by Markets.game
    var price1000 by Markets.price1000
    var price2000 by Markets.price2000
    var price4000 by Markets.price4000
    var price6000 by Markets.price6000
    var price8000 by Markets.price8000
    var price10000 by Markets.price10000
    var price15000 by Markets.price15000

    var price1000Discount by Markets.price1000Discount
    var price2000Discount by Markets.price2000Discount
    var price4000Discount by Markets.price4000Discount
    var price6000Discount by Markets.price6000Discount
    var price8000Discount by Markets.price8000Discount
    var price10000Discount by Markets.price10000Discount
    var price15000Discount by Markets.price15000Discount
}
