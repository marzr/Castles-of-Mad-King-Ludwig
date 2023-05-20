package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonusTile
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

interface LivingRoom : RoomTile {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.LIVING
}

class SmallSquareLivingRoom(
        override val title: String,
        override val doors: List<Door>
) : LivingRoom, SmallSquareRoom {
    override val cornerBonus = 5
}

class LongRectangleLivingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : LivingRoom, LongRectangleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 5
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomPurpose))
}

class SmallCircleLivingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose
) : LivingRoom, SmallCircleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Door(1, listOf(bonusRoomPurpose))
    override val doors: List<Door> = doors(L(), T(), R(), B())
}

class OctagonLivingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : OctagonRoom, LivingRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Door(4, listOf(bonusRoomPurpose))
}

class LargeRectangleLivingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : LivingRoom, LargeRectangleRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 2
    override val centerBonus = CenterBonus.Door(3, listOf(bonusRoomPurpose, RoomPurpose.ACTIVITY))
}

class SquareLivingRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : LivingRoom, CenterBonusTile<CenterBonus.Door>, SquareRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Door(2, listOf(bonusRoomPurpose, RoomPurpose.ACTIVITY))
}

val livingRooms = listOf(
        SmallSquareLivingRoom("Лиловый кабинет", doors(L(), T(), R(2), B(2))),
        SmallSquareLivingRoom("Комната для медитации", doors(L(), T(2), R(2), B(2))),
        SmallSquareLivingRoom("Розовый кабинет", doors(L(), T(2), R(), B(2))),

        LongRectangleLivingRoom("Галерея зеркал", RoomPurpose.CORRIDOR, doors(L(2), T(7), R(2), B())),
        LongRectangleLivingRoom("Салон", RoomPurpose.ACTIVITY, doors(L(2), T(6), R(2), B(5))),

        SmallCircleLivingRoom("Приемная", RoomPurpose.ACTIVITY),
        SmallCircleLivingRoom("Кабинет короля", RoomPurpose.LIVING),
        SmallCircleLivingRoom("Зал ожидания", RoomPurpose.OUTDOOR),

        OctagonLivingRoom("Тронный зал", RoomPurpose.SLEEPING, doors(L(), T(5), R(2), B(5))),
        OctagonLivingRoom("Парадный вестибюль", RoomPurpose.FOOD, doors(L(2), T(3), R(2), B(3))),

        LargeRectangleLivingRoom("Западный гобеленный зал", RoomPurpose.OUTDOOR, doors(L(2), T(6), R(2), B())),
        LargeRectangleLivingRoom("Художественный класс", RoomPurpose.UTILITY, doors(L(2), T(3), R(), B(6))),

        SquareLivingRoom("Восточный гобеленный зал", RoomPurpose.OUTDOOR, doors(L(2), T(3), R(2), B(4))),
        SquareLivingRoom("Обсерватория", RoomPurpose.LIVING, doors(L(3), T(2), R(2), B(3)))
)
