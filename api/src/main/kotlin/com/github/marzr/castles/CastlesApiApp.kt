package com.github.marzr.castles

import com.github.marzr.castles.dao.*
import com.github.marzr.castles.service.GameService
import com.github.marzr.castles.service.MarketDbService
import com.github.marzr.castles.service.PlayerDbService
import com.github.marzr.castles.service.PreGameDbService

fun main() {
    Database().initDatabase
    startServer(
        GameService(
            GameDao(),
            JoinedUserDao(),
            PlayerDbService(PlayerDao(), BonusCardDao()),
            MarketDbService(MarketDao()),
            KingFavorDao(),
        ),
        PreGameDbService(PreGameDao(), JoinedUserDao())
    )
}
