package com.github.marzr.castles.game

import com.github.marzr.castles.data.RoomType
import com.github.marzr.castles.data.rooms.OutdoorRoom
import com.github.marzr.castles.geometry.PositionedTile
import com.github.marzr.castles.geometry.hasContactingFence
import com.github.marzr.castles.geometry.intersects

sealed class TileAddResult {
    data class Success(val reward: InstantReward) : TileAddResult()
    object Fail : TileAddResult()
}

class Castle {
    private val tiles = mutableListOf<PositionedTile>()

    fun addTile(positionedTile: PositionedTile): TileAddResult {

        return if (tileAddViolations(positionedTile).isEmpty()) {
            tiles.add(positionedTile)

            TileAddResult.Success(InstantReward(0))
        } else TileAddResult.Fail
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
    class Intersection(val tiles: List<PositionedTile>) : Violation
    class GardenFenceContact(outdoorTile: PositionedTile) : Violation {
        init {
            if (outdoorTile.tile.roomType != RoomType.OUTDOOR)
                throw IllegalStateException("tile must be outdoor")
        }
    }

    class WrongFloor(val tiles: List<PositionedTile>) : Violation
    object NoDoorContact: Violation
}
