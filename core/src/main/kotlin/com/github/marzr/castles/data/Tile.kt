package com.github.marzr.castles.data

sealed interface Tile {
    val roomPurpose: RoomPurpose
}

sealed class CorridorTile : Tile {
    override val roomPurpose = RoomPurpose.CORRIDOR
}

class Stairs : CorridorTile()

class Foyer : CorridorTile()

class Hallway: CorridorTile()
