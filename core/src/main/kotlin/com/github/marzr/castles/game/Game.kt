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
    val roomsDeck : Deck<RoomTile> = Deck(allRooms), // TODO remove tiles for 2 or 3 players
    val bonusDeck : Deck<BonusCard> = Deck(BonusCard.allBonusCards),
    val market : Market = Market(users.size),
    val players : Players = Players(users),
    var remainingStairs: Int = STAIRS_COUNT[users.size]!!,
    var remainingHallways: Int = HALLWAY_COUNT[users.size]!!,
) {
    companion object {
        val STAIRS_COUNT = mapOf(4 to 6, 3 to 5, 2 to 4)
        val HALLWAY_COUNT = mapOf(4 to 9, 3 to 7, 2 to 5)
    }

    fun startGame() {
        checkBonusesChosen()
    }

    fun checkBonusesChosen() {
        if (players.list.find { it.bonusesToChoose.isNotEmpty() } != null)
            throw IllegalStateException("Players should choose bonus cards")
    }
}
