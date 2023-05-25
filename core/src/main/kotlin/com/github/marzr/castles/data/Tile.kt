package com.github.marzr.castles.data

import com.github.marzr.castles.game.Player

sealed interface Tile {
    val title: String
    val roomPurpose: RoomPurpose
}

sealed class CorridorTile : Tile {
    override val roomPurpose = RoomPurpose.CORRIDOR
}

class Stairs : CorridorTile() {
    override val title = "Stairs"
}

class Foyer(color: Player.PlayerColor): CorridorTile() {
    override val title = "Foyer$color"
}

class Hallway : CorridorTile() {
    override val title = "Hallway"
}
