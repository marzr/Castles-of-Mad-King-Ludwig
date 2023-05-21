package com.github.marzr.castles.dao

import com.github.marzr.castles.entity.MarketEntity
import com.github.marzr.castles.table.Games
import com.github.marzr.castles.table.Markets
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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

    fun persistFullfill(
        gameId: Long,
        price1000: String?,
        price2000: String?,
        price4000: String?,
        price6000: String?,
        price8000: String?,
        price10000: String?,
        price15000: String?,
    ) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            this.price1000?.let { price1000Discount += 1000 }
            this.price2000?.let { price2000Discount += 1000 }
            this.price4000?.let { price4000Discount += 1000 }
            this.price6000?.let { price6000Discount += 1000 }
            this.price8000?.let { price8000Discount += 1000 }
            this.price10000?.let { price10000Discount += 1000 }
            this.price15000?.let { price15000Discount += 1000 }

            this.price1000 = price1000
            this.price2000 = price2000
            this.price4000 = price4000
            this.price6000 = price6000
            this.price8000 = price8000
            this.price10000 = price10000
            this.price15000 = price15000
        }
    }

    fun removePrice1000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price1000 = null
            price1000Discount = 0
        }
    }

    fun removePrice2000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price2000 = null
            price2000Discount = 0
        }
    }

    fun removePrice4000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price4000 = null
            price4000Discount = 0
        }
    }

    fun removePrice6000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price6000 = null
            price6000Discount = 0
        }
    }

    fun removePrice8000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price8000 = null
            price8000Discount = 0
        }
    }

    fun removePrice10000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price10000 = null
            price10000Discount = 0
        }
    }

    fun removePrice15000(gameId: Long) = transaction {
        addLogger(StdOutSqlLogger)
        with(MarketEntity.find(Markets.game eq gameId).first()) {
            price15000 = null
            price15000Discount = 0
        }
    }
}
