package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface FoodRoom : RoomTile {
    override val roomType: RoomType
        get() = RoomType.FOOD
}

class SmallSquareFoodRoom(override val title: String) : FoodRoom, SmallSquareRoom {
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
    bonusRoomType: RoomType
) : CenterBonusFoodRoom(title, bonusRoomType), SmallRectangleRoom

class LFormFoodRoom(
    override val title: String,
    bonusRoomType: RoomType
) : CenterBonusFoodRoom(title, bonusRoomType), LRoom

val foodRooms = listOf(
    SmallSquareFoodRoom("Кухонька"),
    SmallSquareFoodRoom("Склад масла"),
    SmallSquareFoodRoom("Кладовая"),
    SmallRectangleFoodRoom("Мясной склад", RoomType.OUTDOOR),
    SmallRectangleFoodRoom("Мясной склад", RoomType.ACTIVITY),
    SmallRectangleFoodRoom("Мясной склад", RoomType.SLEEPING),
    LFormFoodRoom("Обеденный зал", RoomType.LIVING),
    LFormFoodRoom("Буфетная", RoomType.UTILITY),
    LFormFoodRoom("Кухня", RoomType.FOOD)
)
