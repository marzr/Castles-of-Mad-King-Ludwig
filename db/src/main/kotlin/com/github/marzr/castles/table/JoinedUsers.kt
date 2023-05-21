package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object JoinedUsers : LongIdTable() {
    val name = varchar("name", 50)
    val preGame = reference("pre_game", PreGames)
}
