package org.serverct.parrot.parrotx.ui

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.serverct.parrot.parrotx.function.isNull
import org.serverct.parrot.parrotx.function.textured
import org.serverct.parrot.parrotx.ui.config.advance.ShapeConfiguration
import org.serverct.parrot.parrotx.ui.config.advance.TemplateConfiguration
import taboolib.module.ui.type.Chest
import taboolib.module.ui.type.PageableChest
import taboolib.platform.util.modifyMeta

/**
 * Aiyatsbus
 * com.mcstarrysky.aiyatsbus.module.ingame.ui.internal.Utils
 *
 * @author mical
 */

fun Chest.setSlots(
    shape: ShapeConfiguration,
    templates: TemplateConfiguration,
    key: String,
    vararg args: Pair<String, Any>
) {
    var tot = 0
    shape[key].forEach { slot ->
        val map = args.toMap().mapValues {
            it.value
        }
        set(slot, templates(key, slot, 0, false, "Fallback") { this += map })
        onClick(slot) { templates[it.rawSlot]?.handle(this, it) { this += map } }
        tot++
    }
}

fun Chest.load(
    shape: ShapeConfiguration,
    templates: TemplateConfiguration,
    player: Player,
    vararg ignored: String
) {
    val notAuto = ignored.toMutableList() + "Back"
    onBuild { _, inventory ->
        shape.all(*notAuto.toTypedArray()) { slot, index, item, _ ->
            inventory.setItem(slot, item(slot, index))
        }
    }

    if (shape["Back", true].isNotEmpty())
        setSlots(shape, templates, "Back", "player" to player)

    onClick { event ->
        event.isCancelled = true
        if (event.rawSlot in shape && notAuto.none { shape[it, true].contains(event.rawSlot) }) {
            templates[event.rawSlot]?.handle(this, event, "player" to player)
        }
    }
}

fun <T> PageableChest<T>.pages(
    shape: ShapeConfiguration,
    templates: TemplateConfiguration
) {
    shape["Previous"].forEach { slot -> setPreviousPage(slot) { it, _ -> templates("Previous", slot, it) } }
    shape["Next"].forEach { slot -> setNextPage(slot) { it, _ -> templates("Next", slot, it) } }
}

fun ItemStack.skull(skull: String?): ItemStack {
    skull ?: return this
    if (isNull) return this
    if (itemMeta !is SkullMeta) return this
    return if (skull.length <= 20) modifyMeta<SkullMeta> { owner = skull }
    else textured(skull)
}