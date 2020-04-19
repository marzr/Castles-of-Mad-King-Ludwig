package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

abstract class OutdoorRoom(
        override val title: String,
        override val cornerBonus: Int,
        bonusRoomType: RoomType
) : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomType = RoomType.OUTDOOR
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class BigCircleOutdoorRoom(
        title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : OutdoorRoom(title, 4, bonusRoomType), BigCircleRoom

class MiddleRectangleOutdoorRoom(
        title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : OutdoorRoom(title, 1, bonusRoomType), MiddleRectangleRoom

class OctagonOutdoorRoom(
        title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : OutdoorRoom(title, 5, bonusRoomType), OctagonRoom

class LargeRectangleOutdoorRoom(
        title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : OutdoorRoom(title, 3, bonusRoomType), LargeRectangleRoom

class SquareOutdoorRoom(
        title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : OutdoorRoom(title, 2, bonusRoomType), SquareRoom

val outdoorRooms = listOf(
        BigCircleOutdoorRoom("Теплица", RoomType.LIVING, doors(L(), R())),
        BigCircleOutdoorRoom("Хижина Хундинга", RoomType.OUTDOOR, doors(L(), R(), B())),

        MiddleRectangleOutdoorRoom("Привратницкая", RoomType.OUTDOOR, doors(B(1, 5))),
        MiddleRectangleOutdoorRoom("Крыльцо", RoomType.SLEEPING, doors(B(1, 5))),
        MiddleRectangleOutdoorRoom("Сарай", RoomType.UTILITY, doors(R(2), B())),

        OctagonOutdoorRoom("Конюшни", RoomType.ACTIVITY, doors(L(1, 2), R(1, 2))),
        OctagonOutdoorRoom("Террасы", RoomType.OUTDOOR, doors(L(), R(), B(1, 5))),

        LargeRectangleOutdoorRoom("Каретный гараж", RoomType.FOOD, doors(R(), B(5))),
        LargeRectangleOutdoorRoom("Парадные сады", RoomType.LIVING, doors(R(1, 3), B(5))),

        SquareOutdoorRoom("Французская беседка", RoomType.ACTIVITY, doors(L(3), R(4), B(2))),
        SquareOutdoorRoom("Сад тыкв", RoomType.OUTDOOR, doors(L(2), R(), B(4)))
)
