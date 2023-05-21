package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Markets : LongIdTable() {
    val game = reference("game", Games)
    val price1000 = varchar("price_1000", 50).nullable()
    val price2000 = varchar("price_2000", 50).nullable()
    val price4000 = varchar("price_4000", 50).nullable()
    val price6000 = varchar("price_6000", 50).nullable()
    val price8000 = varchar("price_8000", 50).nullable()
    val price10000 = varchar("price_10000", 50).nullable()
    val price15000 = varchar("price_15000", 50).nullable()

    val price1000Discount = integer("price_1000_discount")
    val price2000Discount = integer("price_2000_discount")
    val price4000Discount = integer("price_4000_discount")
    val price6000Discount = integer("price_6000_discount")
    val price8000Discount = integer("price_8000_discount")
    val price10000Discount = integer("price_10000_discount")
    val price15000Discount = integer("price_15000_discount")
}
