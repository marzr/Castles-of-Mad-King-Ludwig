package com.github.marzr.castles.entity

import com.github.marzr.castles.table.Rooms
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RoomEntity (id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RoomEntity>(Rooms)

    var player by Rooms.player
    var name by Rooms.name
    var x by Rooms.x
    var y by Rooms.y
    var rotation by Rooms.rotation
}
