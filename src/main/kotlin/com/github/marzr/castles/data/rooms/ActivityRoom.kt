package com.github.marzr.castles.data.rooms

import com.github.marzr.castles.data.CenterBonus
import com.github.marzr.castles.data.RoomType

interface ActivityRoom : RoomTile, CenterBonusTile<CenterBonus.Wall> {
    override val roomType: RoomType
        get() = RoomType.ACTIVITY
}

private val silentRooms = listOf(RoomType.LIVING, RoomType.SLEEPING)

class OctagonActivityRoom(
    override val title: String
) : OctagonRoom, ActivityRoom {
    override val cornerBonus = 7
    override val centerBonus = CenterBonus.Wall(
        -2,
        listOf(RoomType.FOOD, RoomType.DOWNSTAIRS, RoomType.UTILITY, RoomType.CORRIDOR) + silentRooms
    )
}

class BigCircleActivityRoom(
    override val title: String
) : BigCircleRoom, ActivityRoom {
    override val cornerBonus = 6
    override val centerBonus = CenterBonus.Wall(
        -2,
        listOf(RoomType.FOOD, RoomType.DOWNSTAIRS) + silentRooms
    )
}

class LongRectangleActivityRoom(
    override val title: String,
    vararg penaltyRoomTypes: RoomType
) : ActivityRoom, LongRectangleRoom {
    override val cornerBonus = 5
    override val centerBonus = CenterBonus.Wall(-1, silentRooms + penaltyRoomTypes.toList())
}

class SmallCircleActivityRoom(
    override val title: String
) : ActivityRoom, SmallCircleRoom {
    override val cornerBonus = 3
    override val centerBonus = CenterBonus.Wall(-1, silentRooms)
}

class MiddleRectangleActivityRoom(
    override val title: String,
    penaltyRoomType: RoomType
) : ActivityRoom, MiddleRectangleRoom {
    override val cornerBonus = 4
    override val centerBonus = CenterBonus.Wall(-1, silentRooms + penaltyRoomType)
}

val activityRooms = listOf(
    OctagonActivityRoom("Певческий зал"),
    OctagonActivityRoom("Зал приемов"),
    BigCircleActivityRoom("Зал заседаний"),
    BigCircleActivityRoom("Театр"),
    LongRectangleActivityRoom("Кегельбан", RoomType.FOOD, RoomType.UTILITY),
    LongRectangleActivityRoom("Игровая", RoomType.CORRIDOR, RoomType.DOWNSTAIRS),
    SmallCircleActivityRoom("Музыкальный класс"),
    SmallCircleActivityRoom("Кабинет Берты"),
    SmallCircleActivityRoom("Фортепианная"),
    MiddleRectangleActivityRoom("Ателье", RoomType.UTILITY),
    MiddleRectangleActivityRoom("Часовня", RoomType.DOWNSTAIRS),
    MiddleRectangleActivityRoom("Бильярдная", RoomType.FOOD)
)
