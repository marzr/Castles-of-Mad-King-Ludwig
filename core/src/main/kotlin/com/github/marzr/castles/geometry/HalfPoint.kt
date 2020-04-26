package com.github.marzr.castles.geometry

import kotlin.math.sqrt

data class HalfPoint(val x: Int, val y: Int) {

    //x and y are supposed to be less than the size of the field * 2
    fun distance(p: HalfPoint): Double = sqrt(0.0 + (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y)) / 2.0
}
