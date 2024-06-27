package org.serverct.parrot.parrotx.update

import org.bukkit.event.player.PlayerJoinEvent
import org.serverct.parrot.parrotx.ParrotX.config
import org.serverct.parrot.parrotx.function.sendMultiMessage
import org.serverct.parrot.parrotx.lang.asLangOrNull
import org.serverct.parrot.parrotx.lang.sendLang
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.pluginId

@Suppress("unused", "MemberVisibilityCanBePrivate")
object UpdateManager {

    var isUpdateAvailable = false
    var latestVersion = ""
    var link = ""

    val checkUpdate: Boolean
        get() = config?.getBoolean("update.check") ?: true
    val notifyUpdate: Boolean
        get() = config?.getBoolean("update.notify") ?: true

    @SubscribeEvent
    fun notifyUpdate(e: PlayerJoinEvent) {
        val player = e.player
        if (checkUpdate && isUpdateAvailable && player.isOp && notifyUpdate) {
            player.apply {
                if (asLangOrNull("update-notice") != null) {
                    sendLang("update-notice", latestVersion)
                } else {
                    sendMultiMessage {
                        +"&f[ $pluginId &f] &aThere is a new update available: &e$latestVersion"
                        +"&f| &aYou can download it from:"
                        +"&f| &e$link"
                        +"&f| &aYou can disable this notice in config file."
                    }
                }
            }
        }
    }

}