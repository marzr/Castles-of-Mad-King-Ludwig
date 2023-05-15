package com.github.marzr.castles.data.bonus

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.RoomPurpose

interface KingsFavor {

    val id: String

    data class RoomAreaKingsFavor(val roomPurpose: RoomPurpose, override val id: String) : KingsFavor {
        companion object {
            fun list() = RoomPurpose.values().map { RoomAreaKingsFavor(it, "RoomAreaKingsFavor_$it") }
        }
    }

    data class RoomCountKingsFavor(val roomPurpose: RoomPurpose, override val id: String) : KingsFavor {
        companion object {
            fun list() = RoomPurpose.values().map { RoomCountKingsFavor(it, "RoomCountKingsFavor_$it") }
        }
    }

    object MoneyFavor : KingsFavor {
        override val id = "MoneyFavor"
    }

    object ExternalEntrancesFavor : KingsFavor {
        override val id = "ExternalEntrancesFavor"
    }

    object CompletedRoomsFavor : KingsFavor {
        override val id = "CompletedRoomsFavor"
    }

    object IncompleteRoomsFavor : KingsFavor {
        override val id = "IncompleteRoomsFavor"
    }

    data class RoomsFavor(val figureTypes: List<FigureType>, override val id: String) : KingsFavor {

        companion object {
            val CIRCULAR_ROOMS_FAVOR =
                RoomsFavor(listOf(FigureType.BIG_CIRCLE, FigureType.SMALL_CIRCLE), "CircularRoomsFavor")

            val SQUARE_ROOMS_FAVOR = RoomsFavor(listOf(FigureType.SMALL_SQUARE, FigureType.SQUARE), "SquareRoomsFavor")

            val SMALL_ROOMS_FAVOR = RoomsFavor(FigureType.values().filter { it.area <= 300 }, "SmallRoomsFavor")

            val LARGE_ROOMS_FAVOR = RoomsFavor(FigureType.values().filter { it.area >= 350 }, "LargeRoomsFavor")
        }
    }

    companion object {
        val allFavors: List<KingsFavor> = RoomAreaKingsFavor.list() + RoomCountKingsFavor.list() +
            MoneyFavor + ExternalEntrancesFavor + CompletedRoomsFavor + IncompleteRoomsFavor +
            RoomsFavor.SMALL_ROOMS_FAVOR + RoomsFavor.CIRCULAR_ROOMS_FAVOR +
            RoomsFavor.LARGE_ROOMS_FAVOR + RoomsFavor.SQUARE_ROOMS_FAVOR
    }
}
