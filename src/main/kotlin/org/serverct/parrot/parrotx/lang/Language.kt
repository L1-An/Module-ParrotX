package org.serverct.parrot.parrotx.lang

import org.bukkit.command.CommandSender
import org.serverct.parrot.parrotx.ParrotX.config
import taboolib.platform.util.asLangText
import taboolib.platform.util.asLangTextList
import taboolib.platform.util.asLangTextOrNull
import taboolib.platform.util.sendLang

object Language {

    val isSoundNotify: Boolean
        get() = config?.getBoolean("sound-notify") ?: true

    fun sendLang(sender: CommandSender, key: String, vararg args: Any) {
        sender.sendLang(key, *args, sender.asLangTextOrNull("prefix") to "prefix")
    }

    fun getLang(sender: CommandSender, key: String, vararg args: Any): String {
        return sender.asLangText(key, *args, sender.asLangTextOrNull("prefix") to "prefix")
    }

    fun getLangOrNull(sender: CommandSender, key: String, vararg args: Any): String? {
        return sender.asLangTextOrNull(key, *args, sender.asLangTextOrNull("prefix") to "prefix")
    }

    fun getLangList(sender: CommandSender, key: String, vararg args: Any): List<String> {
        return sender.asLangTextList(key, *args, sender.asLangTextOrNull("prefix") to "prefix")
    }

}