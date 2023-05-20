package com.github.marzr.castles.data

import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.rooms.*

interface RoomTile : Tile {
    val title: String
    val figureType: FigureType
    val cornerBonus: Int
    val doors: List<Door>
}

interface CenterBonusTile<CB : CenterBonus> : Tile {
    val centerBonus: CB
}

val allRooms = activityRooms as List<RoomTile> + corridorRooms + downstairsRooms + foodRooms + livingRooms +
        outdoorRooms + sleepingRooms + utilityRooms

val roomsByTitle = allRooms.associateBy { it.title }
