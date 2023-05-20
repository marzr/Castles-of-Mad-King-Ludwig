package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.RoomPurpose

interface UtilityRoom : RoomTile {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.UTILITY
}

class SmallSquareUtilityRoom(
        override val title: String,
        override val doors: List<Door>
) : UtilityRoom, SmallSquareRoom {
    override val cornerBonus = 1
}

class SmallRectangleUtilityRoom(
        override val title: String,
        override val doors: List<Door>
) : UtilityRoom, SmallRectangleRoom {
    override val cornerBonus = 1
}

class LFormUtilityRoom(
        override val title: String,
        override val doors: List<Door>
) : UtilityRoom, LRoom {
    override val cornerBonus = 3
}

val utilityRooms = listOf(
        SmallSquareUtilityRoom("Будуар", doors(B(2))),
        SmallSquareUtilityRoom("Чулан", doors(B())),
        SmallSquareUtilityRoom("Гардероб", doors(B())),

        SmallRectangleUtilityRoom("Склад посуды", doors(R(2))),
        SmallRectangleUtilityRoom("Ванная", doors(B(3))),
        SmallRectangleUtilityRoom("Комната для чистки", doors(R())),

        LFormUtilityRoom("Потайная комната", doors(R())),
        LFormUtilityRoom("Сауна", doors(R(4))),
        LFormUtilityRoom("Прачечная", doors(R(3)))
)
