@file:Suppress("unused")

package org.serverct.parrot.parrotx.function

import java.math.RoundingMode

fun Int.next(range: IntRange): Int {
    var next = this + 1
    if (next > range.last) {
        next = range.first
    }
    return next
}

fun Int.last(range: IntRange): Int {
    var last = this - 1
    if (last < range.first) {
        last = range.last
    }
    return last
}

fun Double.round(scale: Int = 2): Double = toBigDecimal().setScale(scale, RoundingMode.HALF_DOWN).toDouble()

/**
 * 以 value 的概率返回 true
 * @param value 概率值
 * @param max 最大值(也就是 100% 的值, 默认为 100)
 */
fun chance(value: Double, max: Int = 100) : Boolean = Math.random() * max < value