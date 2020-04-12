package com.github.marzr.castles.data.rooms

class Door(val side: Side, val index: Int) {

    enum class Side {
        TOP, BOTTOM, LEFT, RIGHT
    }
}

fun doors(vararg doors: List<Door>) = doors.flatMap { it }

/**
 * Left side doors
 */
fun L(vararg index: Int = intArrayOf(1)) = index.toDoors(Door.Side.LEFT)

/**
 * Right side doors
 */
fun R(vararg index: Int = intArrayOf(1)) = index.toDoors(Door.Side.RIGHT)

/**
 * Top side doors
 */
fun T(vararg index: Int = intArrayOf(1)) = index.toDoors(Door.Side.TOP)

/**
 * Bottom side doors
 */
fun B(vararg index: Int = intArrayOf(1)) = index.toDoors(Door.Side.BOTTOM)

private fun IntArray.toDoors(side: Door.Side): List<Door> = this.map { Door(side, it) }