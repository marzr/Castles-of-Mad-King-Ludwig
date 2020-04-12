package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface OutdoorRoom : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomType: RoomType
        get() = RoomType.OUTDOOR
}

class BigCircleOutdoorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : BigCircleRoom, OutdoorRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class MiddleRectangleOutdoorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : OutdoorRoom, MiddleRectangleRoom {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class OctagonOutdoorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : OctagonRoom, OutdoorRoom {
    override val cornerBonus = 5
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class LargeRectangleOutdoorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : OutdoorRoom, LargeRectangleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class SquareOutdoorRoom(
    override val title: String,
    bonusRoomType: RoomType
) : OutdoorRoom, SquareRoom {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

val outdoorRooms = listOf(
    BigCircleOutdoorRoom("Теплица", RoomType.LIVING),
    BigCircleOutdoorRoom("Хижина Хундинга", RoomType.OUTDOOR),
    MiddleRectangleOutdoorRoom("Привратницкая", RoomType.OUTDOOR),
    MiddleRectangleOutdoorRoom("Крыльцо", RoomType.SLEEPING),
    MiddleRectangleOutdoorRoom("Сарай", RoomType.UTILITY),
    OctagonOutdoorRoom("Конюшни", RoomType.ACTIVITY),
    OctagonOutdoorRoom("Террасы", RoomType.OUTDOOR),
    LargeRectangleOutdoorRoom("Каретный гараж", RoomType.FOOD),
    LargeRectangleOutdoorRoom("Парадные сады", RoomType.LIVING),
    SquareOutdoorRoom("Французская беседка", RoomType.ACTIVITY),
    SquareOutdoorRoom("Сад тыкв", RoomType.OUTDOOR)
)
