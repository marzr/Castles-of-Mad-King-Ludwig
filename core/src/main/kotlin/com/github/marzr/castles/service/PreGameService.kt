package com.github.marzr.castles.service

import com.github.marzr.castles.dao.PreGameDao
import com.github.marzr.castles.game.PreGame

class PreGameService(private val preGameDao : PreGameDao) {

    fun create() = PreGame(preGameDao.create().id.value)
}
