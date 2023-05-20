package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonusTile
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

abstract class OutdoorRoom(
    override val title: String,
    override val cornerBonus: Int,
    bonusRoomPurpose: RoomPurpose
) : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomPurpose = RoomPurpose.OUTDOOR
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomPurpose))
}

class BigCircleOutdoorRoom(
    title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OutdoorRoom(title, 4, bonusRoomPurpose), BigCircleRoom

class MiddleRectangleOutdoorRoom(
    title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OutdoorRoom(title, 1, bonusRoomPurpose), MiddleRectangleRoom

class OctagonOutdoorRoom(
    title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OutdoorRoom(title, 5, bonusRoomPurpose), OctagonRoom

class LargeRectangleOutdoorRoom(
    title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OutdoorRoom(title, 3, bonusRoomPurpose), LargeRectangleRoom

class SquareOutdoorRoom(
    title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OutdoorRoom(title, 2, bonusRoomPurpose), SquareRoom

val outdoorRooms = listOf(
        BigCircleOutdoorRoom("Теплица", RoomPurpose.LIVING, doors(L(), R())),
        BigCircleOutdoorRoom("Хижина Хундинга", RoomPurpose.OUTDOOR, doors(L(), R(), B())),

        MiddleRectangleOutdoorRoom("Привратницкая", RoomPurpose.OUTDOOR, doors(B(1, 5))),
        MiddleRectangleOutdoorRoom("Крыльцо", RoomPurpose.SLEEPING, doors(B(1, 5))),
        MiddleRectangleOutdoorRoom("Сарай", RoomPurpose.UTILITY, doors(R(2), B())),

        OctagonOutdoorRoom("Конюшни", RoomPurpose.ACTIVITY, doors(L(1, 2), R(1, 2))),
        OctagonOutdoorRoom("Террасы", RoomPurpose.OUTDOOR, doors(L(), R(), B(1, 5))),

        LargeRectangleOutdoorRoom("Каретный гараж", RoomPurpose.FOOD, doors(R(), B(5))),
        LargeRectangleOutdoorRoom("Парадные сады", RoomPurpose.LIVING, doors(R(1, 3), B(5))),

        SquareOutdoorRoom("Французская беседка", RoomPurpose.ACTIVITY, doors(L(3), R(4), B(2))),
        SquareOutdoorRoom("Сад тыкв", RoomPurpose.OUTDOOR, doors(L(2), R(), B(4)))
)
