package org.serverct.parrot.parrotx.feature

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.serverct.parrot.parrotx.lang.LanguageType
import org.serverct.parrot.parrotx.lang.asLangOrNull
import org.serverct.parrot.parrotx.lang.sendLang
import org.serverct.parrot.parrotx.util.show
import taboolib.common.platform.command.bool
import taboolib.common.platform.command.subCommand
import taboolib.module.chat.colored

@Suppress("unused")
object Debug {

    private var debugStatus: Boolean = false

    fun Player.debug(message: String, vararg args: Any) {
        if (debugStatus && isOp) {
            if (asLangOrNull(message) != null) {
                sendLang(message, *args, type = LanguageType.Info)
            } else {
                sendMessage(message.colored())
            }
        }
    }

    val CommandDebug = subCommand {
        // xx debug <boolean>

        execute<CommandSender> { sender, _, _ ->
            debugStatus = !debugStatus
            sender.sendLang("debug", debugStatus.show() to "status", type = LanguageType.Info)
        }

        bool("boolean") {
            suggestion<CommandSender> { _, _ ->
                listOf("true", "false")
            }

            execute<CommandSender> { sender, ctx, _ ->
                debugStatus = ctx["boolean"].toBoolean()
                sender.sendLang("debug", debugStatus.show() to "status", type = LanguageType.Info)
            }
        }
    }

}