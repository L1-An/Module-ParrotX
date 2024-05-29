@file:Suppress("unused")

package org.serverct.parrot.parrotx.function

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.serverct.parrot.parrotx.lang.sendLang


fun OfflinePlayer.notice(node: String, vararg args: Any): Boolean {
    if (!isOnline) {
        return false
    }
    return runCatching {
        player!!.sendLang(node, *args)
        true
    }.getOrElse { false }
}

@Deprecated("Rename to \"notice\"", ReplaceWith("notice(node, *args)"))
fun OfflinePlayer.trySendLang(node: String, vararg args: Any): Boolean = notice(node, *args)

infix fun Player.move(destination: Location): Boolean {
    val ref = location
    val to = destination.clone().apply {
        yaw = ref.yaw
        pitch = ref.pitch
    }

    with(to.block.getRelative(BlockFace.DOWN)) {
        if (!type.isSolid) {
            breakNaturally()
            type = Material.GLASS
        }
    }
    return teleport(to)
}