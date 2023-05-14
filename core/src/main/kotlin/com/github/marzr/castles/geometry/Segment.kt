package com.github.marzr.castles.geometry

import java.lang.Integer.max
import java.lang.Integer.min

class Segment(val a: Int, val b: Int)

infix fun Segment.intersects(other: Segment): Boolean =
    max(this.a, other.a) < min(this.b, other.b)
