package com.github.marzr.castles.game

import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.BonusCard
import com.github.marzr.castles.data.bonus.KingsFavor
import com.github.marzr.castles.data.allRooms
import java.lang.IllegalStateException

@ExperimentalStdlibApi
class Game(
    users: List<String>,
    val id: Long,
    val kingsFavors: List<KingsFavor> = Deck(KingsFavor.allFavors).issue(users.size),
    val roomsDeck : Deck<RoomTile> = Deck(allRooms),
    val bonusDeck : Deck<BonusCard> = Deck(BonusCard.allBonusCards),
    val market : Market = Market(users.size),
    val players : Players = Players(users)
) {
    fun startGame() {
        checkBonusesChosen()
    }

    fun checkBonusesChosen() {
        if (players.list.find { it.bonusesToChoose.isNotEmpty() } != null)
            throw IllegalStateException("Players should choose bonus cards")
    }
}
