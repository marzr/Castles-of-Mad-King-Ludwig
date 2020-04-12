package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface FoodRoom : RoomTile {
    override val roomType: RoomType
        get() = RoomType.FOOD
}

class SmallSquareFoodRoom(
    override val title: String,
    override val doors: List<Door>
) : FoodRoom, SmallSquareRoom {
    override val cornerBonus = 2
}

abstract class CenterBonusFoodRoom(
    override val title: String,
    bonusRoomType: RoomType
) : FoodRoom, CenterBonusTile<CenterBonus.Door> {
    override val cornerBonus = 1
    override val centerBonus = CenterBonus.Door(3, listOf(bonusRoomType))
}

class SmallRectangleFoodRoom(
    override val title: String,
    bonusRoomType: RoomType,
    override val doors: List<Door>
) : CenterBonusFoodRoom(title, bonusRoomType), SmallRectangleRoom

class LFormFoodRoom(
    override val title: String,
    bonusRoomType: RoomType,
    override val doors: List<Door>
) : CenterBonusFoodRoom(title, bonusRoomType), LRoom

val foodRooms = listOf(
    SmallSquareFoodRoom("Кухонька", doors(L(2), R(2))),
    SmallSquareFoodRoom("Склад масла", doors(L(), R(2))),
    SmallSquareFoodRoom("Кладовая", doors(L(2), R())),

    SmallRectangleFoodRoom("Мясной склад", RoomType.OUTDOOR, doors(L(), R())),
    SmallRectangleFoodRoom("Заготовочная", RoomType.ACTIVITY, doors(T(4), B(4))),
    SmallRectangleFoodRoom("Прихожая", RoomType.SLEEPING, doors(T(2), B(3))),

    LFormFoodRoom("Обеденный зал", RoomType.LIVING, doors(L(2), R(3))),
    LFormFoodRoom("Буфетная", RoomType.UTILITY, doors(L(4), R(3))),
    LFormFoodRoom("Кухня", RoomType.FOOD, doors(R(2), B(2)))
)
