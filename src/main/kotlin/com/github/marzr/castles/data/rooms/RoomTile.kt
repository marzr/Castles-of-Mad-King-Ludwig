package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.Tile

interface RoomTile : Tile {
    val title: String
    val figureType: FigureType
    val cornerBonus: Int
}

interface CenterBonusTile<CB : CenterBonus> : Tile {
    val centerBonus: CB
}

