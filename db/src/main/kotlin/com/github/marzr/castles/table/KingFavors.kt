package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object KingFavors : LongIdTable() {
    val name = varchar("name", 50)
    val game = reference("game", Games)
}
