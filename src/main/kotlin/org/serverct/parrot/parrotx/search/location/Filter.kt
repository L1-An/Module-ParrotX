package org.serverct.parrot.parrotx.search.location

import org.bukkit.Location
import org.bukkit.block.BlockFace
import org.serverct.parrot.parrotx.search.api.filter.Filter
import java.util.concurrent.CompletableFuture

/**
 * 用于过滤位置的可站立过滤器
 * 传入一个Location，若该位置是可站立的，则返回true，否则返回false
 * 使用方法：
 * ```kotlin
 * fun safeTeleport(player: Player, location: Location) {
 *     StandableFilter.predict(location).thenAccept { // 过滤位置
 *         if (it) {
 *             player.teleport(location)
 *         }
 *     }
 * }
 * ```
 */
@Suppress("MemberVisibilityCanBePrivate", "unused")
object StandableFilter : Filter<Location> {

    override fun predict(value: Location): CompletableFuture<Boolean> {
        return CompletableFuture.completedFuture(value.y >= 0 && standable(value))
    }

    fun standable(at: Location): Boolean {
        if (at.world == null) {
            return false
        }
        val block = at.block ?: return false
        return if (block.type.isSolid) {
            block.getRelative(BlockFace.UP).isEmpty && block.getRelative(BlockFace.UP, 2).isEmpty
        } else {
            !block.isEmpty && !block.isLiquid && block.getRelative(BlockFace.DOWN).type.isSolid && block.getRelative(BlockFace.UP).isEmpty
        }
    }

}