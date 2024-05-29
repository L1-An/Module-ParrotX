@file:Suppress("unused", "DEPRECATION")

package org.serverct.parrot.parrotx.util

import com.google.gson.JsonParser
import taboolib.module.chat.colored

val JSON_PARSER = JsonParser()

/** 将 Boolean 显示为符号 */
@JvmName("booleanShowBoolean")
fun Boolean.show() = if (this) "&a✔".colored() else "&c✘".colored()

@JvmName("stringShowBoolean")
/** 若 String 为 Boolean 则显示为符号 */
fun String.show() = if (this.toBooleanStrict()) "&a✔".colored() else "&c✘".colored()