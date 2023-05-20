package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonusTile
import com.github.marzr.castles.data.RoomTile
import com.github.marzr.castles.data.bonus.CenterBonus
import com.github.marzr.castles.data.RoomPurpose

interface ActivityRoom : RoomTile, CenterBonusTile<CenterBonus.Wall> {
    override val roomPurpose: RoomPurpose
        get() = RoomPurpose.ACTIVITY
}

private val silentRooms = listOf(RoomPurpose.LIVING, RoomPurpose.SLEEPING)

class OctagonActivityRoom(
        override val title: String,
        override val doors: List<Door>
) : OctagonRoom, ActivityRoom {
    override val cornerBonus = 7
    override val centerBonus = CenterBonus.Wall(
            -2,
            listOf(RoomPurpose.FOOD, RoomPurpose.DOWNSTAIRS, RoomPurpose.UTILITY, RoomPurpose.CORRIDOR) + silentRooms
    )
}

class BigCircleActivityRoom(
        override val title: String,
        override val doors: List<Door>
) : BigCircleRoom, ActivityRoom {
    override val cornerBonus = 6
    override val centerBonus = CenterBonus.Wall(
            -2,
            listOf(RoomPurpose.FOOD, RoomPurpose.DOWNSTAIRS) + silentRooms
    )
}

class LongRectangleActivityRoom(
        override val title: String,
        override val doors: List<Door>,
        vararg penaltyRoomPurposes: RoomPurpose
) : ActivityRoom, LongRectangleRoom {
    override val cornerBonus = 5
    override val centerBonus = CenterBonus.Wall(-1, silentRooms + penaltyRoomPurposes.toList())
}

class SmallCircleActivityRoom(
        override val title: String,
        override val doors: List<Door>
) : ActivityRoom, SmallCircleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Wall(-1, silentRooms)
}

class MiddleRectangleActivityRoom(
    override val title: String,
    penaltyRoomPurpose: RoomPurpose,
    override val doors: List<Door>
) : ActivityRoom, MiddleRectangleRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Wall(-1, silentRooms + penaltyRoomPurpose)
}

val activityRooms = listOf(
        OctagonActivityRoom("Певческий зал", doors(B(2, 3, 4))),
        OctagonActivityRoom("Зал приемов", doors(T(2), R(1, 2))),

        BigCircleActivityRoom("Зал заседаний", doors(T(), R(), B())),
        BigCircleActivityRoom("Театр", doors(L(), T(), R())),

        LongRectangleActivityRoom("Кегельбан", doors(L(), T(), B()), RoomPurpose.FOOD, RoomPurpose.UTILITY),
        LongRectangleActivityRoom("Игровая", doors(T(6), R(2), B(6)), RoomPurpose.CORRIDOR, RoomPurpose.DOWNSTAIRS),

        SmallCircleActivityRoom("Музыкальный класс", doors(L(), T(), R())),
        SmallCircleActivityRoom("Кабинет Берты", doors(L(), R(), B())),
        SmallCircleActivityRoom("Фортепианная", doors(T(), R(), B())),

        MiddleRectangleActivityRoom("Ателье", RoomPurpose.UTILITY, doors(T(4), R(2), B(4))),
        MiddleRectangleActivityRoom("Часовня", RoomPurpose.DOWNSTAIRS, doors(T(3), R(1, 2))),
        MiddleRectangleActivityRoom("Бильярдная", RoomPurpose.FOOD, doors(T(3), R(), B(2)))
)
