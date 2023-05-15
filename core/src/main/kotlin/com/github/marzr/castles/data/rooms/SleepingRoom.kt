package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

interface SleepingRoom : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.SLEEPING
}

class SquareSleepingRoom(
        override val title: String,
        override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SquareRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(2, listOf(RoomPurpose.CORRIDOR))
}

class SmallRectangleSleepingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SmallRectangleRoom {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomPurpose))
}

class LFormSleepingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, LRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomPurpose))
}

val sleepingRooms = listOf(
        SquareSleepingRoom("Большая спальня", doors(L(), B(4))),
        SquareSleepingRoom("Комната для слуг", doors(L(3), B(2))),

        SmallRectangleSleepingRoom("Комната отдыха", RoomPurpose.LIVING, doors(R(2), B(2))),
        SmallRectangleSleepingRoom("Гардеробная", RoomPurpose.FOOD, doors(L(2), R())),
        SmallRectangleSleepingRoom("Солярий", RoomPurpose.OUTDOOR, doors(R(2), B(4))),

        LFormSleepingRoom("Комната Тассо", RoomPurpose.LIVING, doors(L(), B(4))),
        LFormSleepingRoom("Спальня королевы", RoomPurpose.SLEEPING, doors(T(), R())),
        LFormSleepingRoom("Гостевая", RoomPurpose.FOOD, doors(R(4), B(2)))
)
