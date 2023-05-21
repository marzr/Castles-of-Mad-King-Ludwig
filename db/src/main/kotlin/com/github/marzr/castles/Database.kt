package com.github.marzr.castles

import com.github.marzr.castles.table.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class Database {
    val initDatabase by lazy {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Games)
            SchemaUtils.create(PreGames)
            SchemaUtils.create(JoinedUsers)
            SchemaUtils.create(Players)
            SchemaUtils.create(BonusCards)
            SchemaUtils.create(Markets)
            SchemaUtils.create(KingFavors)
        }
    }
}
