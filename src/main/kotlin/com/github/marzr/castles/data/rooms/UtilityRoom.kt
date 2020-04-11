package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.RoomType

interface UtilityRoom : RoomTile {
    override val roomType: RoomType
        get() = RoomType.UTILITY
}

class SmallSquareUtilityRoom(override val title: String) : UtilityRoom, SmallSquareRoom {
    override val cornerBonus = 1
}

class SmallRectangleUtilityRoom(
    override val title: String
) : UtilityRoom, SmallRectangleRoom {
    override val cornerBonus = 1
}

class LFormUtilityRoom(override val title: String) : UtilityRoom, LRoom {
    override val cornerBonus = 3
}

val utilityRooms = listOf(
    SmallSquareUtilityRoom("Будуар"),
    SmallSquareUtilityRoom("Чулан"),
    SmallSquareUtilityRoom("Гардероб"),
    SmallRectangleUtilityRoom("Мясной склад"),
    SmallRectangleUtilityRoom("Заготовочная"),
    SmallRectangleUtilityRoom("Прихожая"),
    LFormUtilityRoom("Потайная комната"),
    LFormUtilityRoom("Сауна"),
    LFormUtilityRoom("Прачечная")
)