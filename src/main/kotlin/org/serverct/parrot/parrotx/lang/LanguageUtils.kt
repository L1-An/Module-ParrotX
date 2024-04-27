@file:Suppress("unused")
package org.serverct.parrot.parrotx.lang

import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.platform.util.asLangTextOrNull

/**
 * 给CommandSender发送语言信息
 * @param node 语言文件的键
 * @param args 语言文件的参数
 * @param type 语言类型(Info, Error, Done), 默认为 null (不播放音效)
 */
fun CommandSender.sendLang(node: String, vararg args: Any, type: LanguageType? = null) {
    Language.sendLang(this, node, *args, asLangTextOrNull("prefix") to "prefix")
    // 播放信息音效
    if (this is Player) playSound(this, type)
}

/**
 * 获取语言文件
 * @param node 语言文件的键
 * @param args 语言文件的参数
 */
fun CommandSender.asLangOrNull(node: String, vararg args: Any): String? {
    return Language.getLangOrNull(this, node, *args, asLangTextOrNull("prefix") to "prefix")
}

/**
 * 获取语言文件
 * @param node 语言文件的键
 * @param args 语言文件的参数
 */
fun CommandSender.asLang(node: String, vararg args: Any): String {
    return Language.getLang(this, node, *args, asLangTextOrNull("prefix") to "prefix")
}

/**
 * 获取语言文件列表
 * @param node 语言文件的键
 * @param args 语言文件的参数
 */
fun CommandSender.getLangList(node: String, vararg args: Any): List<String> {
    return Language.getLangList(this, node, *args, asLangTextOrNull("prefix") to "prefix")
}

private fun playSound(player: Player, type: LanguageType?) {
    when (type) {
        LanguageType.Info -> player.playSound(player.location, Sound.UI_BUTTON_CLICK, 1f, (1..2).random().toFloat())
        LanguageType.Error -> player.playSound(player.location, Sound.ENTITY_VILLAGER_NO, 1f, (1..2).random().toFloat())
        LanguageType.Done -> player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, (1..2).random().toFloat())
        null -> {}
    }
}