@file:Suppress("unused")

package org.serverct.parrot.parrotx.function

import org.bukkit.entity.Player
import taboolib.common.platform.function.console
import taboolib.module.chat.colored

/** 向控制台发送信息 */
fun sendInfo(message: String) {
    console().sendMessage(message.colored())
}

/**
 * 向控制台发送多条信息
 * ```kotlin
 * sendInfo {
 *     +"test"
 *     +"test2"
 *     +...
 * }
 * ```
 */
fun sendInfo(block: InfoBuilder.() -> Unit) {
    val builder = InfoBuilder()
    builder.block()
    builder.texts.forEach { sendInfo(it) }
}

fun Player.sendMultiMessage(block: InfoBuilder.() -> Unit) {
    val builder = InfoBuilder()
    builder.block()
    builder.texts.forEach { sendMessage(it.colored()) }
}

interface StringReceiver {
    operator fun String.unaryPlus()
}

class InfoBuilder : StringReceiver {
    val texts = mutableListOf<String>()
    override fun String.unaryPlus() {
        texts.add(this)
    }
}
