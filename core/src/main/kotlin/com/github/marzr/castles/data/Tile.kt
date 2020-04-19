package com.github.marzr.castles.data

interface Tile {
    val roomType: RoomType
}

abstract class CorridorTile : Tile {
    override val roomType = RoomType.CORRIDOR
}

class Stairs : CorridorTile()

class Foyer : CorridorTile()

class Hallway: CorridorTile()
