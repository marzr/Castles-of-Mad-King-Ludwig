package com.github.marzr.castles.data

interface Tile {
    val roomPurpose: RoomPurpose
}

abstract class CorridorTile : Tile {
    override val roomPurpose = RoomPurpose.CORRIDOR
}

class Stairs : CorridorTile()

class Foyer : CorridorTile()

class Hallway: CorridorTile()
