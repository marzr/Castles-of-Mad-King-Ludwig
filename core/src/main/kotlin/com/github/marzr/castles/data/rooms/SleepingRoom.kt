package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomType

interface SleepingRoom : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomType: RoomType
        get() = RoomType.SLEEPING
}

class SquareSleepingRoom(
        override val title: String,
        override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SquareRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(2, listOf(RoomType.CORRIDOR))
}

class SmallRectangleSleepingRoom(
        override val title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SmallRectangleRoom {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType))
}

class LFormSleepingRoom(
        override val title: String,
        bonusRoomType: RoomType,
        override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, LRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType))
}

val sleepingRooms = listOf(
        SquareSleepingRoom("Большая спальня", doors(L(), B(4))),
        SquareSleepingRoom("Комната для слуг", doors(L(3), B(2))),

        SmallRectangleSleepingRoom("Комната отдыха", RoomType.LIVING, doors(R(2), B(2))),
        SmallRectangleSleepingRoom("Гардеробная", RoomType.FOOD, doors(L(2), R())),
        SmallRectangleSleepingRoom("Солярий", RoomType.OUTDOOR, doors(R(2), B(4))),

        LFormSleepingRoom("Комната Тассо", RoomType.LIVING, doors(L(), B(4))),
        LFormSleepingRoom("Спальня королевы", RoomType.SLEEPING, doors(T(), R())),
        LFormSleepingRoom("Гостевая", RoomType.FOOD, doors(R(4), B(2)))
)
