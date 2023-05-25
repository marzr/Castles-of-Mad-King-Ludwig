package com.github.marzr.castles.game

import com.github.marzr.castles.data.Foyer
import com.github.marzr.castles.data.RoomPurpose
import com.github.marzr.castles.geometry.*

sealed class TileAddResult {
    data class Success(val reward: InstantReward) : TileAddResult()
    data class Fail(val violations: List<Violation>) : TileAddResult()
}

class Castle {
    private val tiles = mutableListOf<PositionedTile>()

    fun addTile(positionedTile: PositionedTile): TileAddResult {
        if (positionedTile.tile is Foyer && tiles.isEmpty()) {
            tiles.add(positionedTile)
            return TileAddResult.Success(InstantReward(0))
        }

        val violations = tileAddViolations(positionedTile)
        if (violations.isNotEmpty())
            return TileAddResult.Fail(violations)

        tiles.add(positionedTile)
        return TileAddResult.Success(InstantReward(0))
    }

    private fun tileAddViolations(tile: PositionedTile): List<Violation> {
        val result = mutableListOf<Violation>()
        val intersections = mutableListOf<PositionedTile>()
        val doorContacts = mutableMapOf<PositionedTile, Set<DoorPosition>>()
        val wrongFloor = mutableListOf<PositionedTile>()
        tiles.forEach {
            if (tile.intersects(it)) intersections.add(it)
            if (tile.hasContactingFence(it)) result.add(Violation.GardenFenceContact(tile))
            if (it.hasContactingFence(tile)) result.add(Violation.GardenFenceContact(it))
            doorContacts[it] = doorContact(tile, it)
        }
        if (intersections.isNotEmpty())
            result.add(Violation.Intersection(intersections))

        if (doorContacts.isEmpty()) result.add(Violation.NoDoorContact)
        if (tile.tile.roomPurpose != RoomPurpose.DOWNSTAIRS) {
            doorContacts.forEach { (it, _) ->
                if (it.tile.roomPurpose == RoomPurpose.DOWNSTAIRS)
                    wrongFloor.add(it)
            }
        }
        if (tile.tile.roomPurpose == RoomPurpose.DOWNSTAIRS) {
            doorContacts.forEach { (it, _) ->
                if (it.tile.roomPurpose != RoomPurpose.DOWNSTAIRS)
                    wrongFloor.add(it)
            }
        }

        if (wrongFloor.isNotEmpty())
            result.add(Violation.WrongFloor(wrongFloor))

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
    object NoDoorContact : Violation
}
