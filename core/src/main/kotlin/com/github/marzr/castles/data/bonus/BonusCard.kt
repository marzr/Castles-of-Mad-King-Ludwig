package com.github.marzr.castles.data.bonus

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.RoomPurpose

interface BonusCard {

    val id: String
    val points: Int

    data class RoomPurposeBonusCard private constructor(
        override val points: Int,
        val roomPurpose: RoomPurpose,
        override val id: String
    ) : BonusCard {

        companion object {
            private fun points(roomPurpose: RoomPurpose): Int = when (roomPurpose) {
                RoomPurpose.LIVING -> 2
                RoomPurpose.ACTIVITY -> 2
                RoomPurpose.SLEEPING -> 3
                RoomPurpose.OUTDOOR -> 2
                RoomPurpose.UTILITY -> 2
                RoomPurpose.FOOD -> 3
                RoomPurpose.CORRIDOR -> 1
                RoomPurpose.DOWNSTAIRS -> 2
            }

            fun listCards() =
                RoomPurpose.values().map { RoomPurposeBonusCard(points(it), it, "RoomPurposeBonusCard_$it") }
        }
    }

    data class FigureTypeBonusCard(override val points: Int, val figureType: FigureType, override val id: String) :
        BonusCard {
        companion object {
            private fun points(figureType: FigureType): Int = when (figureType) {
                FigureType.BIG_CIRCLE -> 3
                FigureType.SMALL_CIRCLE -> 2
                FigureType.SQUARE -> 3
                FigureType.SMALL_SQUARE -> 2
                FigureType.L_ROOM -> 2
                FigureType.LARGE_RECTANGLE -> 3
                FigureType.LONG_RECTANGLE -> 3
                FigureType.MIDDLE_RECTANGLE -> 2
                FigureType.SMALL_RECTANGLE -> 2
                FigureType.LARGE_OCTAGON -> 3
            }

            fun listCards() = FigureType.values().map { FigureTypeBonusCard(points(it), it, "FigureTypeBonusCard_$it") }
        }
    }

    object UniqueSizeBonusCard : BonusCard {
        override val id = "UniqueSizeBonusCard"
        override val points = 8
    }

    object GalleriesBonusCard : BonusCard {
        override val id = "GalleriesBonusCard"
        override val points = 1
    }

    object ExternalEntrancesBonusCard : BonusCard {
        override val id = "ExternalEntrancesBonusCard"
        override val points = 1
    }

    object SquareRoomsBonusCard : BonusCard {
        override val id = "SquareRoomsBonusCard"
        override val points = 1
    }

    object MoneyBonusCard : BonusCard {
        override val id = "MoneyBonusCard"
        override val points = 1
    }

    object UniqueTypeBonusCard : BonusCard {
        override val id = "UniqueTypeBonusCard"
        override val points = 7
    }

    object StairsBonusCard : BonusCard {
        override val id = "StairsBonusCard"
        override val points = 2
    }

    object CompletedRoomsBonusCard : BonusCard {
        override val id = "CompletedRoomsBonusCard"
        override val points = 1
    }

    object CircularRoomsBonusCard : BonusCard {
        override val id = "CircularRoomsBonusCard"
        override val points = 1
    }

    companion object {
        val allBonusCards = RoomPurposeBonusCard.listCards() + FigureTypeBonusCard.listCards() +
                UniqueSizeBonusCard + GalleriesBonusCard + ExternalEntrancesBonusCard +
                SquareRoomsBonusCard + MoneyBonusCard + UniqueTypeBonusCard +
                StairsBonusCard + CompletedRoomsBonusCard + CircularRoomsBonusCard
    }
}
