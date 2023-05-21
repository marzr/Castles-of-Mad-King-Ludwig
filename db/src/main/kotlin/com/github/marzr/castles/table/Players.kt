package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Players : LongIdTable() {
    val name = varchar("name", 50)
    val game = reference("game", Games)
    val money = integer("money")
    val color = varchar("color", 6)
}
