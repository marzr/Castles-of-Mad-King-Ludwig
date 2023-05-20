package com.github.marzr.castles.data

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

class Foyer: CorridorTile() {
    override val title = "Foyer"
}

class Hallway : CorridorTile() {
    override val title = "Hallway"
}
