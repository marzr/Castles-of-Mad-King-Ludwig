package com.github.marzr.castles.data

interface BonusCard {

    companion object{
        val allBonusCards = RoomBonusCard.listCards() + SizeBonusCard.listCards()
    }

    data class RoomBonusCard private constructor(val points: Int, val roomType: RoomType) : BonusCard {

        companion object {
            private fun points(roomType: RoomType): Int = when (roomType) {
                RoomType.LIVING -> 2
                RoomType.ACTIVITY -> 2
                RoomType.SLEEPING -> 3
                RoomType.OUTDOOR -> 2
                RoomType.UTILITY -> 2
                RoomType.FOOD -> 3
                RoomType.CORRIDOR -> 1
                RoomType.DOWNSTAIRS -> 2
            }

            fun listCards() = RoomType.values().map { RoomBonusCard(points(it), it) }
        }
    }

    data class SizeBonusCard(val points: Int, val figureType: FigureType) {
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

            fun listCards() = FigureType.values().map { SizeBonusCard(points(it), it) }
        }
    }
}

