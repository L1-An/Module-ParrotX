@file:Suppress("unused")

package org.serverct.parrot.parrotx.function

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*
import java.util.concurrent.TimeUnit

fun usernameOrNull(uniqueId: UUID): String? {
    return Bukkit.getOfflinePlayer(uniqueId).name
}

fun username(uniqueId: UUID): String {
    return usernameOrNull(uniqueId) ?: "$uniqueId"
}

fun user(user: OfflinePlayer, fallbackName: String? = null): String {
    return "${user.name ?: fallbackName ?: "null"}(${user.uniqueId})"
}

fun user(uniqueId: UUID, fallbackName: String? = null): String {
    return "${usernameOrNull(uniqueId) ?: fallbackName ?: "null"}($uniqueId)"
}

/**
 * 将秒数转换为可读的时间格式
 * @param seconds 秒数
 * @param map<TimeUnit, String> 单位映射
 * @return 可读的时间格式
 */
fun duration(
    seconds: Long,
    units: Map<TimeUnit, String> = mapOf(
        TimeUnit.HOURS to "小时",
        TimeUnit.MINUTES to "分钟",
        TimeUnit.SECONDS to "秒"
    )
): String {
    var left = seconds
    val builder = StringBuilder()
    units.forEach { (unit, name) ->
        val duration = unit.toSeconds(1)
        val value = left / duration
        if (value <= 0) {
            return@forEach
        }
        if (builder.isNotEmpty()) {
            builder.append(" ")
        }
        builder.append("$value $name")
        left %= duration
    }
    return builder.toString().trim()
}