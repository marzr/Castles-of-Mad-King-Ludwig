package com.github.marzr.castles.game

import com.github.marzr.castles.data.RoomPurpose
import com.github.marzr.castles.geometry.PositionedTile
import com.github.marzr.castles.geometry.hasContactingFence
import com.github.marzr.castles.geometry.intersects

sealed class TileAddResult {
    data class Success(val reward: InstantReward) : TileAddResult()
    data class Fail(val violations: List<Violation>) : TileAddResult()
}

class Castle {
    private val tiles = mutableListOf<PositionedTile>()

    fun addTile(positionedTile: PositionedTile): TileAddResult {
        val violations=tileAddViolations(positionedTile)
        return if (violations.isEmpty()) {
            tiles.add(positionedTile)

            TileAddResult.Success(InstantReward(0))
        } else TileAddResult.Fail(violations)
    }

    private fun tileAddViolations(tile: PositionedTile): List<Violation> {
        val result = mutableListOf<Violation>()
        val intersections = mutableListOf<PositionedTile>()
        tiles.forEach {
            if (tile.intersects(it)) intersections.add(it)
            if (tile.hasContactingFence(it)) result.add(Violation.GardenFenceContact(tile))
            if (it.hasContactingFence(tile)) result.add(Violation.GardenFenceContact(it))

        }
        if (intersections.isNotEmpty())
            result.add(Violation.Intersection(intersections))
        return result.toList()
    }
}

sealed interface Violation {
    class Intersection(val tiles: List<PositionedTile>) : Violation {
        override fun toString(): String = tiles.joinToString { "${it.tile.title}, ${it.position}" }
            .let { "Intersection[$it]" }
    }
    class GardenFenceContact(val outdoorTile: PositionedTile) : Violation {
        init {
            if (outdoorTile.tile.roomPurpose != RoomPurpose.OUTDOOR)
                throw IllegalStateException("tile must be outdoor")
        }
    }

    class WrongFloor(val tiles: List<PositionedTile>) : Violation
    object NoDoorContact: Violation
}
