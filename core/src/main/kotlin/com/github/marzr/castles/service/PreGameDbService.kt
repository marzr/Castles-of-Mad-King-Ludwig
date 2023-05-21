package com.github.marzr.castles.service

import com.github.marzr.castles.dao.JoinedUserDao
import com.github.marzr.castles.dao.PreGameDao
import com.github.marzr.castles.game.PreGame

class PreGameDbService(
    private val preGameDao: PreGameDao,
    private val joinedUserDao: JoinedUserDao
) {

    fun create(currentUser: String): PreGame {
        val gameId = preGameDao.create().id.value
        joinedUserDao.joinGame(currentUser, gameId)
        return PreGame(gameId, listOf(currentUser))
    }

    fun join(user: String, preGameId: Long): PreGame {
        joinedUserDao.joinGame(user, preGameId)
        return PreGame(preGameId, joinedUserDao.getJoinedUsers(preGameId))
    }
}
