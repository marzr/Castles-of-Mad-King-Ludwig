package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface SleepingRoom : RoomTile, CenterBonusTile<CenterBonus.Door> {
    override val roomType: RoomType
        get() = RoomType.FOOD
}

class SquareSleepingRoom(
    override val title: String
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SquareRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(2, listOf(RoomType.CORRIDOR))
}

class SmallRectangleSleepingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, SmallRectangleRoom {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType))
}

class LFormSleepingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : SleepingRoom, CenterBonusTile<CenterBonus.Door>, LRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType))
}

val sleepingRooms = listOf(
    SquareSleepingRoom("Большая спальня"),
    SquareSleepingRoom("Комната для слуг"),
    SmallRectangleSleepingRoom("Комната отдыха", RoomType.LIVING),
    SmallRectangleSleepingRoom("Гардеробная", RoomType.FOOD),
    SmallRectangleSleepingRoom("Солярий", RoomType.OUTDOOR),
    LFormSleepingRoom("Комната Тассо", RoomType.LIVING),
    LFormSleepingRoom("Спальня королевы", RoomType.SLEEPING),
    LFormSleepingRoom("Гостевая", RoomType.FOOD)
)
