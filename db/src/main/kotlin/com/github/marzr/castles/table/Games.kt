package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Games : LongIdTable() {
    val playersCount = integer("players_count")
}