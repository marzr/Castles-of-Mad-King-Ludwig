package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

abstract class DownstairsRoom : RoomTile, CenterBonusTile<CenterBonus.Downstairs> {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.DOWNSTAIRS
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
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : DownstairsRoom(), SmallCircleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Downstairs(3, bonusRoomPurpose)
}

class LargeRectangleDownstairsRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : DownstairsRoom(), LargeRectangleRoom {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Downstairs(2, bonusRoomPurpose)
}

val downstairsRooms = listOf(
    MiddleRectangleDownstairsRoom("Зал Героев", CenterBonus.Downstairs(2, RoomPurpose.SLEEPING), doors(L(2), B(5))),
    MiddleRectangleDownstairsRoom("Склеп", CenterBonus.Downstairs(1, RoomPurpose.LIVING), doors(L(2), B(5))),
    MiddleRectangleDownstairsRoom("Замшелый подвал", CenterBonus.Downstairs(2, RoomPurpose.FOOD), doors(L(2), B(3))),

    BigCircleDownstairsRoom("Грот Венеры", CenterBonus.Downstairs(1, RoomPurpose.CORRIDOR), doors(L(), R())),
    BigCircleDownstairsRoom("Секретное логово", CenterBonus.Downstairs(2, RoomPurpose.UTILITY), doors(L(), B())),

    SmallCircleDownstairsRoom("Темница", RoomPurpose.SLEEPING, doors(B())),
    SmallCircleDownstairsRoom("Сад грибов", RoomPurpose.FOOD, doors(L(), R())),
    SmallCircleDownstairsRoom("Бездонный колодец", RoomPurpose.OUTDOOR, doors(T())),

    LargeRectangleDownstairsRoom("Арсенал", RoomPurpose.ACTIVITY, doors(R(2), B(5))),
    LargeRectangleDownstairsRoom("Тюрьма", RoomPurpose.DOWNSTAIRS, doors(R(3), B(3)))
)
