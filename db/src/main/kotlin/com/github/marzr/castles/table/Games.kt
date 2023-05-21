package com.github.marzr.castles.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Games : LongIdTable() {
    val remainingStairs = integer("remaining_stairs")
    val remainingHallways = integer("remaining_hallways")
}