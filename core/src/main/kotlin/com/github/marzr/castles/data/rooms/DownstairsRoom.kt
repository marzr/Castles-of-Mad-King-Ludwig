package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

abstract class DownstairsRoom : RoomTile, CenterBonusTile<CenterBonus.Downstairs> {
    override val roomType: RoomType
        get() = RoomType.DOWNSTAIRS
}

class MiddleRectangleDownstairsRoom(
    override val title: String,
    override val centerBonus: CenterBonus.Downstairs,
    override val doors: List<Door>
) : DownstairsRoom(), MiddleRectangleRoom {
    override val cornerBonus = 2
}

class BigCircleDownstairsRoom(
    override val title: String,
    override val centerBonus: CenterBonus.Downstairs,
    override val doors: List<Door>
) : DownstairsRoom(), BigCircleRoom {
    override val cornerBonus = 1
}

class SmallCircleDownstairsRoom(
    override val title: String,
    bonusRoomType: RoomType,
    override val doors: List<Door>
) : DownstairsRoom(), SmallCircleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Downstairs(3, bonusRoomType)
}

class LargeRectangleDownstairsRoom(
    override val title: String,
    bonusRoomType: RoomType,
    override val doors: List<Door>
) : DownstairsRoom(), LargeRectangleRoom {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Downstairs(2, bonusRoomType)
}

val downstairsRooms = listOf(
    MiddleRectangleDownstairsRoom("Зал Героев", CenterBonus.Downstairs(2, RoomType.SLEEPING), doors(L(2), B(5))),
    MiddleRectangleDownstairsRoom("Склеп", CenterBonus.Downstairs(1, RoomType.LIVING), doors(L(2), B(5))),
    MiddleRectangleDownstairsRoom("Замшелый подвал", CenterBonus.Downstairs(2, RoomType.FOOD), doors(L(2), B(3))),

    BigCircleDownstairsRoom("Грот Венеры", CenterBonus.Downstairs(1, RoomType.CORRIDOR), doors(L(), R())),
    BigCircleDownstairsRoom("Секретное логово", CenterBonus.Downstairs(2, RoomType.UTILITY), doors(L(), B())),

    SmallCircleDownstairsRoom("Темница", RoomType.SLEEPING, doors(B())),
    SmallCircleDownstairsRoom("Сад грибов", RoomType.FOOD, doors(L(), R())),
    SmallCircleDownstairsRoom("Бездонный колодец", RoomType.OUTDOOR, doors(T())),

    LargeRectangleDownstairsRoom("Арсенал", RoomType.ACTIVITY, doors(R(2), B(5))),
    LargeRectangleDownstairsRoom("Тюрьма", RoomType.DOWNSTAIRS, doors(R(3), B(3)))
)
