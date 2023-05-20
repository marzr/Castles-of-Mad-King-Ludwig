package com.github.marzr.castles

import com.github.marzr.castles.dao.PreGameDao
import com.github.marzr.castles.service.GameService
import com.github.marzr.castles.service.PreGameService

fun main() {
    Database().initDatabase
    startServer(GameService(), PreGameService(PreGameDao()))
}
