package com.github.marzr.castles.data.bonus

import com.github.marzr.castles.data.RoomType

open class CenterBonus(
    open val points: Int,
    val wall: WallType,
    open val roomTypes: List<RoomType>
) {

    enum class WallType {
        WALL,
        DOOR,
        NONE
    }

    data class Door(
        override val points: Int,
        override val roomTypes: List<RoomType>
    ) : CenterBonus(points, WallType.DOOR, roomTypes)

    data class Downstairs(
        override val points: Int,
        val roomType: RoomType
    ) : CenterBonus(points, WallType.NONE, listOf(roomType))

    data class Wall(
        override val points: Int,
        override val roomTypes: List<RoomType>
    ) : CenterBonus(points, WallType.WALL, roomTypes)
}
