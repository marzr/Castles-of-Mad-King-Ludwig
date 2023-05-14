package com.github.marzr.castles.game

import com.github.marzr.castles.data.bonus.BonusCard

data class Player(
    val name: String,
    val color: PlayerColor,

    // TODO move to safe class PlayerState
    var money: Int = 0,
    val bonuses: MutableList<BonusCard> = mutableListOf(),
    val bonusesToChoose: MutableList<BonusCard> = mutableListOf(),
    var points: Int = 0,
    val castle: Castle = Castle()
    // move
) {

    enum class PlayerColor {
        RED, BLUE, GREEN, YELLOW
    }
}
