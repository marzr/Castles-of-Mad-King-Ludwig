package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard

data class Player(
    val id: Long,
    val name: String,
    var money: Int = 0,
    val color: PlayerColor,
    val bonuses: MutableList<BonusCard> = mutableListOf(),
    val bonusesToChoose: MutableList<BonusCard> = mutableListOf(),
    var points: Int = 0,
    val castle: Castle = Castle()
) {
    enum class PlayerColor {
        RED, BLUE, GREEN, YELLOW
    }
}
