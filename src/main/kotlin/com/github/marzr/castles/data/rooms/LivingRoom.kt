package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface LivingRoom : RoomTile {
    override val roomType: RoomType
        get() = RoomType.ACTIVITY
}

class SmallSquareLivingRoom(override val title: String) : LivingRoom, SmallSquareRoom {
    override val cornerBonus = 5
}

class LongRectangleLivingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : LivingRoom, LongRectangleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 5
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType))
}

class SmallCircleLivingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : LivingRoom, SmallCircleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomType))
}

class OctagonLivingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : OctagonRoom, LivingRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Door(4, listOf(bonusRoomType))
}

class LargeRectangleLivingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : LivingRoom, LargeRectangleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(3, listOf(bonusRoomType, RoomType.ACTIVITY))
}

class SquareLivingRoom(
    override val title: String,
    bonusRoomType: RoomType
) : LivingRoom, CenterBonusTile<CenterBonus.Door>, SquareRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomType, RoomType.ACTIVITY))
}

val livingRooms = listOf(
    SmallSquareLivingRoom("Лиловый кабинет"),
    SmallSquareLivingRoom("Комната для медитации"),
    SmallSquareLivingRoom("Розовый кабинет"),
    LongRectangleLivingRoom("Галерея зеркал", RoomType.CORRIDOR),
    LongRectangleLivingRoom("Салон", RoomType.ACTIVITY),
    SmallCircleLivingRoom("Приемная", RoomType.ACTIVITY),
    SmallCircleLivingRoom("Кабинет короля", RoomType.LIVING),
    SmallCircleLivingRoom("Зал ожидания", RoomType.OUTDOOR),
    OctagonLivingRoom("Тронный зал", RoomType.SLEEPING),
    OctagonLivingRoom("Парадный вестибюль", RoomType.FOOD),
    LargeRectangleLivingRoom("Западный гобеленный зал", RoomType.OUTDOOR),
    LargeRectangleLivingRoom("Художественный класс", RoomType.UTILITY),
    SquareLivingRoom("Восточный гобеленный зал", RoomType.OUTDOOR),
    SquareLivingRoom("Обсерватория", RoomType.LIVING)
)
