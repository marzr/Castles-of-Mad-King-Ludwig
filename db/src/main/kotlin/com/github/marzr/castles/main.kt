package com.github.marzr.castles

import com.github.marzr.castles.entity.Game
import com.github.marzr.castles.table.Games
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

//TODO remove example
fun main() {
    Database.connect(
        "jdbc:postgresql://localhost:5433/castles", driver = "org.postgresql.Driver",
        user = "idp", password = "idp"
    )

    transaction {
        // print sql to std-out
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(Games)

        Game.new {
            playersCount = 4
        }

        println("Games: ${Game.all().map { it.id }}")
    }
}
