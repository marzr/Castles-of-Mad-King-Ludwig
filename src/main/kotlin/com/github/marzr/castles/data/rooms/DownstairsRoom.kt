package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface DownstairsRoom : RoomTile, CenterBonusTile<CenterBonus.Downstairs> {
    override val roomType: RoomType
        get() = RoomType.DOWNSTAIRS
}

class MiddleRectangleDownstairsRoom(
    override val title: String,
    override val centerBonus: CenterBonus.Downstairs
) : DownstairsRoom, MiddleRectangleRoom {
    override val cornerBonus = 2
}

class BigCircleDownstairsRoom(
    override val title: String,
    override val centerBonus: CenterBonus.Downstairs
) : DownstairsRoom, BigCircleRoom {
    override val cornerBonus = 1
}

class SmallCircleDownstairsRoom(
    override val title: String,
    bonusRoomType: RoomType
) : DownstairsRoom, SmallCircleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Downstairs(3, bonusRoomType)
}

class LargeRectangleDownstairsRoom(
    override val title: String,
    bonusRoomType: RoomType
) : DownstairsRoom, LargeRectangleRoom {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Downstairs(2, bonusRoomType)
}

val downstairsRooms = listOf(
    MiddleRectangleDownstairsRoom("Зал Героев", CenterBonus.Downstairs(2, RoomType.SLEEPING)),
    MiddleRectangleDownstairsRoom("Склеп", CenterBonus.Downstairs(1, RoomType.LIVING)),
    MiddleRectangleDownstairsRoom("Замшелый подвал", CenterBonus.Downstairs(2, RoomType.FOOD)),
    BigCircleDownstairsRoom("Грот Венеры", CenterBonus.Downstairs(1, RoomType.CORRIDOR)),
    BigCircleDownstairsRoom("Секретное логово", CenterBonus.Downstairs(2, RoomType.UTILITY)),
    SmallCircleDownstairsRoom("Темница", RoomType.SLEEPING),
    SmallCircleDownstairsRoom("Сад грибов", RoomType.FOOD),
    SmallCircleDownstairsRoom("Бездонный колодец", RoomType.OUTDOOR),
    LargeRectangleDownstairsRoom("Арсенал", RoomType.ACTIVITY),
    LargeRectangleDownstairsRoom("Тюрьма", RoomType.DOWNSTAIRS)
)
