package com.github.marzr.castles.data.bonus

import com.github.marzr.castles.data.RoomPurpose

open class CenterBonus(
    open val points: Int,
    val wall: WallType,
    open val roomPurposes: List<RoomPurpose>
) {

    enum class WallType {
        WALL,
        DOOR,
        NONE
    }

    data class Door(
        override val points: Int,
        override val roomPurposes: List<RoomPurpose>
    ) : CenterBonus(points, WallType.DOOR, roomPurposes)

    data class Downstairs(
        override val points: Int,
        val roomPurpose: RoomPurpose
    ) : CenterBonus(points, WallType.NONE, listOf(roomPurpose))

    data class Wall(
        override val points: Int,
        override val roomPurposes: List<RoomPurpose>
    ) : CenterBonus(points, WallType.WALL, roomPurposes)
}
