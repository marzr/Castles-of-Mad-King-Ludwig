package com.github.marzr.castles.data

interface KingsFavor {

    data class RoomAreaKingsFavor(val roomType: RoomType) : KingsFavor {
        companion object {
            fun list() = RoomType.values().map { RoomAreaKingsFavor(it) }
        }
    }

    data class RoomCountKingsFavor(val roomType: RoomType) : KingsFavor {
        companion object {
            fun list() = RoomType.values().map { RoomCountKingsFavor(it) }
        }
    }

    object MoneyFavor : KingsFavor

    object ExternalEntrancesFavor : KingsFavor

    object CompletedRoomsFavor : KingsFavor

    object IncompleteRoomsFavor : KingsFavor

    data class RoomsFavor(val figureTypes: List<FigureType>) : KingsFavor {

        companion object {
            val CIRCULAR_ROOMS_FAVOR = RoomsFavor(listOf(FigureType.BIG_CIRCLE, FigureType.SMALL_CIRCLE))

            val SQUARE_ROOMS_FAVOR = RoomsFavor(listOf(FigureType.SMALL_SQUARE, FigureType.SQUARE))

            val SMALL_ROOMS_FAVOR = RoomsFavor(FigureType.values().filter { it.area <= 300 })

            val LARGE_ROOMS_FAVOR = RoomsFavor(FigureType.values().filter { it.area >= 350 })
        }
    }

    companion object {
        val allFavors: List<KingsFavor> = RoomAreaKingsFavor.list() + RoomCountKingsFavor.list() +
                MoneyFavor + ExternalEntrancesFavor + CompletedRoomsFavor + IncompleteRoomsFavor +
                RoomsFavor.SMALL_ROOMS_FAVOR + RoomsFavor.CIRCULAR_ROOMS_FAVOR +
                RoomsFavor.LARGE_ROOMS_FAVOR + RoomsFavor.SQUARE_ROOMS_FAVOR
    }
}
