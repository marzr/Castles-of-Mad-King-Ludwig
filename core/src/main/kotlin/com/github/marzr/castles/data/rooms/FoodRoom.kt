package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonusTile
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

interface FoodRoom : RoomTile {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.FOOD
}

class SmallSquareFoodRoom(
        override val title: String,
        override val doors: List<Door>
) : FoodRoom, SmallSquareRoom {
    override val cornerBonus = 2
}

abstract class CenterBonusFoodRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose
) : FoodRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Door(3, listOf(bonusRoomPurpose))
}

class SmallRectangleFoodRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : CenterBonusFoodRoom(title, bonusRoomPurpose), SmallRectangleRoom

class LFormFoodRoom(
    override val title: String,
    bonusRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : CenterBonusFoodRoom(title, bonusRoomPurpose), LRoom

val foodRooms = listOf(
        SmallSquareFoodRoom("Кухонька", doors(L(2), R(2))),
        SmallSquareFoodRoom("Склад масла", doors(L(), R(2))),
        SmallSquareFoodRoom("Кладовая", doors(L(2), R())),

        SmallRectangleFoodRoom("Мясной склад", RoomPurpose.OUTDOOR, doors(L(), R())),
        SmallRectangleFoodRoom("Заготовочная", RoomPurpose.ACTIVITY, doors(T(4), B(4))),
        SmallRectangleFoodRoom("Прихожая", RoomPurpose.SLEEPING, doors(T(2), B(3))),

        LFormFoodRoom("Обеденный зал", RoomPurpose.LIVING, doors(L(2), R(3))),
        LFormFoodRoom("Буфетная", RoomPurpose.UTILITY, doors(L(4), R(3))),
        LFormFoodRoom("Кухня", RoomPurpose.FOOD, doors(R(2), B(2)))
)
