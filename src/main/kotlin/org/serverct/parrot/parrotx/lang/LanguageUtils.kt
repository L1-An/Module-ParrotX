package org.serverct.parrot.parrotx.lang

import org.bukkit.command.CommandSender
import taboolib.platform.util.asLangTextOrNull

// 用 Language 重写的方法发送语言文件
fun CommandSender.sendLang(node: String, vararg args: Any) {
    Language.sendLang(this, node, *args, asLangTextOrNull("prefix") to "prefix")
}

// 用 Language 重写的方法获取语言文件
fun CommandSender.asLangOrNull(node: String, vararg args: Any): String? {
    return Language.getLangOrNull(this, node, *args, asLangTextOrNull("prefix") to "prefix")
}

// 用 Language 重写的方法获取语言文件
fun CommandSender.asLang(key: String, vararg args: Any): String {
    return Language.getLang(this, key, *args, asLangTextOrNull("prefix") to "prefix")
}

// 用 Language 重写的方法获取语言文件
fun CommandSender.getLangList(key: String, vararg args: Any): List<String> {
    return Language.getLangList(this, key, *args, asLangTextOrNull("prefix") to "prefix")
}