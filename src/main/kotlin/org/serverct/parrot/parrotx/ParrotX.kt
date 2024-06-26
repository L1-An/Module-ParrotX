package org.serverct.parrot.parrotx

import taboolib.common.platform.function.info
import taboolib.common.util.replaceWithOrder
import taboolib.module.configuration.ConfigLoader

@Suppress("MemberVisibilityCanBePrivate", "unused")
object ParrotX {

    val isDebugMode: Boolean by lazy { System.getProperty("parrotx.debug", "false").toBooleanStrict() }
    val config = ConfigLoader.files["config.yml"]?.configuration

    fun debug(message: String, vararg args: Any?) {
        if (isDebugMode) {
            info(message.replaceWithOrder(*args.map { it ?: "null" }.toTypedArray()))
        }
    }

}