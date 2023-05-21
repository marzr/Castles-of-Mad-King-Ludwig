package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Markets : LongIdTable() {
    val game = reference("game", Games)
    val price1000 = varchar("price1000", 50).nullable()
    val price2000 = varchar("price2000", 50).nullable()
    val price4000 = varchar("price4000", 50).nullable()
    val price6000 = varchar("price6000", 50).nullable()
    val price8000 = varchar("price8000", 50).nullable()
    val price10000 = varchar("price10000", 50).nullable()
    val price15000 = varchar("price15000", 50).nullable()

    val price1000Discount = integer("price1000Discount")
    val price2000Discount = integer("price2000Discount")
    val price4000Discount = integer("price4000Discount")
    val price6000Discount = integer("price6000Discount")
    val price8000Discount = integer("price8000Discount")
    val price10000Discount = integer("price10000Discount")
    val price15000Discount = integer("price15000Discount")
}
