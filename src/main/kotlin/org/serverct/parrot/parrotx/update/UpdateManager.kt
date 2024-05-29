package org.serverct.parrot.parrotx.update

import org.bukkit.event.player.PlayerJoinEvent
import org.serverct.parrot.parrotx.function.sendMultiMessage
import org.serverct.parrot.parrotx.lang.asLangOrNull
import org.serverct.parrot.parrotx.lang.sendLang
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.pluginId
import taboolib.module.configuration.ConfigNode

@Suppress("unused", "MemberVisibilityCanBePrivate")
object UpdateManager {

    var isUpdateAvailable = false
    var latestVersion = ""
    var link = ""

    @ConfigNode("update.check", "config.yml")
    val checkUpdate: Boolean = true

    @ConfigNode("update.notify", "config.yml")
    val notifyUpdate: Boolean = true

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