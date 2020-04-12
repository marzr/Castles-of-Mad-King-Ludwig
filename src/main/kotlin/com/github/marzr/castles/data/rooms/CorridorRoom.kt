package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

class CorridorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : RoomTile, CenterBonusTile<CenterBonus.Door>, LongRectangleRoom {
    override val cornerBonus = 1
    override val roomType = RoomType.CORRIDOR
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
    override val doors = doors(L(1, 2), T(2, 4, 6), R(1, 2), B(1, 3, 5, 7))
}

val corridorRooms = listOf(
    CorridorRoom("Верхний зал", RoomType.FOOD),
    CorridorRoom("Главный зал", RoomType.UTILITY)
)
