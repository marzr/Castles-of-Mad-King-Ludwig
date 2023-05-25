package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Rooms : LongIdTable() {
    val player = reference("player", Players)
    val name = varchar("name", 50)
    val x = integer("x")
    val y = integer("y")
    val rotation = varchar("rotation", 4)
}
