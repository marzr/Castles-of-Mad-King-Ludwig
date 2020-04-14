package com.github.marzr.castles.game

import com.github.marzr.castles.data.BonusCard

data class Player(
    val name: String,
    val color: PlayerColor,

    // TODO move to safe class PlayerState
    var money: Int,
    val bonuses: MutableList<BonusCard>,
    var points: Int,
    val castle: Castle
    // move
) {

    enum class PlayerColor {
        RED, BLUE, GREEN, YELLOW
    }
}
