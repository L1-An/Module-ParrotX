package org.serverct.parrot.parrotx.util

import taboolib.common5.cdouble
import taboolib.common5.clong

/**
 * 时间转换工具
 * 提供了 tick ms s 之间的转换
 * tick -> ms / ms -> tick
 * tick -> s / s -> tick
 * ms -> s / s -> ms
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object TimeUtils {

    //TODO 优化代码，增加一个转换方法，传入两个参数，一个是转换的单位，一个是转换的目标单位

    /**
     * 将 ms 转换为 s
     */
    fun msToSecond(ms: Long): Double = (ms / 1000).toDouble()
    val Long.msToSecond
        get() = msToSecond(this)

    /**
     * 将 tick 转换为 s
     */
    fun tickToSecond(tick: Long): Double = (tick / 20).toDouble()
    val Long.tickToSecond
        get() = tickToSecond(this)
    val Double.tickToSecond
        get() = tickToSecond(this.clong)
    val Int.tickToSecond
        get() = tickToSecond(this.clong)

    /**
     * 将 ms 转换为 tick
     */
    fun msToTick(ms: Long): Int = (ms / 1000 * 20).toInt()
    val Long.msToTick
        get() = msToTick(this)

    /**
     * 将 tick 转换为 ms
     */
    fun tickToMs(tick: Long): Long = tick / 20 * 1000
    val Long.tickToMs
        get() = tickToMs(this)
    val Double.tickToMs
        get() = tickToMs(this.clong)
    val Int.tickToMs
        get() = tickToMs(this.clong)

    /**
     * 将 s 转换为 tick
     */
    fun sToTick(second: Double): Int = (second * 20).toInt()
    val Int.sToTick
        get() = sToTick(this.cdouble)
    val Double.sToTick
        get() = sToTick(this)

    /**
     * 将 s 转换为 ms
     */
    fun sToMs(second: Double): Long = (second * 1000).toLong()
    val Int.sToMs
        get() = sToMs(this.cdouble)
    val Double.sToMs
        get() = sToMs(this)

}