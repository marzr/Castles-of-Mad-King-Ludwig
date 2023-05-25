package com.github.marzr.castles.geometry

fun doorContact(tileA: PositionedTile, tileB: PositionedTile): Set<DoorPosition>
    = tileA.doorPositions() intersect tileB.doorPositions()
