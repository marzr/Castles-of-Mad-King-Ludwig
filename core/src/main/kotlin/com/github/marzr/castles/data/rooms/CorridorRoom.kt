package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonusTile
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

class CorridorRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose
) : RoomTile, CenterBonusTile<CenterBonus.Door>, LongRectangleRoom {
    override val cornerBonus = 1
    override val roomPurpose = RoomPurpose.CORRIDOR
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomPurpose))
    override val doors = doors(L(1, 2), T(2, 4, 6), R(1, 2), B(1, 3, 5, 7))
}

val corridorRooms = listOf(
    CorridorRoom("Верхний зал", RoomPurpose.FOOD),
    CorridorRoom("Главный зал", RoomPurpose.UTILITY)
)
