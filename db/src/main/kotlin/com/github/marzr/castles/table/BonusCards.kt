package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object BonusCards : LongIdTable() {
    val name = varchar("name", 50)
    val player = reference("player", Players)
    val toChoose = bool("to_choose")
}
