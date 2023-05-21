package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.MarketEntity
import com.github.marzr.castles.table.Games
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class MarketDao {

    fun create(
        gameId: Long,
        price1000: String?,
        price2000: String?,
        price4000: String?,
        price6000: String?,
        price8000: String?,
        price10000: String?,
        price15000: String?,
        price1000Discount: Int,
        price2000Discount: Int,
        price4000Discount: Int,
        price6000Discount: Int,
        price8000Discount: Int,
        price10000Discount: Int,
        price15000Discount: Int,
    ) = transaction {
        addLogger(StdOutSqlLogger)
        MarketEntity.new {
            game = EntityID(gameId, Games)
            this.price1000 = price1000
            this.price2000 = price2000
            this.price4000 = price4000
            this.price6000 = price6000
            this.price8000 = price8000
            this.price10000 = price10000
            this.price15000 = price15000

            this.price1000Discount = price1000Discount
            this.price2000Discount = price2000Discount
            this.price4000Discount = price4000Discount
            this.price6000Discount = price6000Discount
            this.price8000Discount = price8000Discount
            this.price10000Discount = price10000Discount
            this.price15000Discount = price15000Discount
        }
    }
}