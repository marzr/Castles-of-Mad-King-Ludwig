package com.github.marzr.castles.data.bonus

import com.github.marzr.castles.data.FigureType
import com.github.marzr.castles.data.RoomPurpose

interface BonusCard {

    val id: String

    data class RoomPurposeBonusCard private constructor(
        val points: Int,
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

    data class FigureTypeBonusCard(val points: Int, val figureType: FigureType, override val id: String) : BonusCard {
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
    }

    object GalleriesBonusCard : BonusCard {
        override val id = "GalleriesBonusCard"
    }

    object ExternalEntrancesBonusCard : BonusCard {
        override val id = "ExternalEntrancesBonusCard"
    }

    object SquareRoomsBonusCard : BonusCard {
        override val id = "SquareRoomsBonusCard"
    }

    object MoneyBonusCard : BonusCard {
        override val id = "MoneyBonusCard"
    }

    object UniqueTypeBonusCard : BonusCard {
        override val id = "UniqueTypeBonusCard"
    }

    object StairsBonusCard : BonusCard {
        override val id = "StairsBonusCard"
    }

    object CompletedRoomsBonusCard : BonusCard {
        override val id = "CompletedRoomsBonusCard"
    }

    object CircularRoomsBonusCard : BonusCard {
        override val id = "CircularRoomsBonusCard"
    }

    companion object {
        val allBonusCards = RoomPurposeBonusCard.listCards() + FigureTypeBonusCard.listCards() +
                UniqueSizeBonusCard + GalleriesBonusCard + ExternalEntrancesBonusCard +
                SquareRoomsBonusCard + MoneyBonusCard + UniqueTypeBonusCard +
                StairsBonusCard + CompletedRoomsBonusCard + CircularRoomsBonusCard
    }
}
