package com.github.marzr.castles.service

import com.github.marzr.castles.dao.JoinedUsersDao
import com.github.marzr.castles.dao.PreGameDao
import com.github.marzr.castles.game.PreGame

class PreGameService(
    private val preGameDao: PreGameDao,
    private val joinedUsersDao: JoinedUsersDao
) {

    fun create(currentUser: String): PreGame {
        val gameId = preGameDao.create().id.value
        joinedUsersDao.joinGame(currentUser, gameId)
        return PreGame(gameId, listOf(currentUser))
    }

    fun join(user: String, preGameId: Long): PreGame {
        joinedUsersDao.joinGame(user, preGameId)
        return PreGame(preGameId, joinedUsersDao.getJoinedUsers(preGameId))
    }
}
