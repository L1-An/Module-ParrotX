package org.serverct.parrot.parrotx.update

import org.serverct.parrot.parrotx.feature.sendInfo
import taboolib.common.platform.function.pluginId
import taboolib.common.platform.function.submitAsync
import java.net.URL
import java.util.*
import java.util.function.Consumer

@Suppress("unused")
/**
 * 基于 SpigotMC API 检测版本号的更新检查器
 * 使用方法：
 * ```kotlin
 * SpigotUpdateChecker(12345).getVersion {
 *     if (pluginVersion == it) {
 *         sendInfo(&f[ $pluginId &f] There is not a new update available.)
 *     } else {
 *         sendInfo {
 *             +"&f[ $pluginId &f] There is a new update available at:"
 *             +"https://www.spigotmc.org/resources/12345/"
 *         }
 *     }
 * }
 * ```
 */
class SpigotUpdateChecker(private val resourceId: Int) {
    fun getVersion(consumer: Consumer<String?>) {
        submitAsync {
            runCatching {
                URL("https://api.spigotmc.org/legacy/update.php?resource=$resourceId/~")
                    .openStream().use { stream ->
                        Scanner(stream).use { scanner ->
                            if (scanner.hasNext()) {
                                consumer.accept(scanner.next())
                            }
                        }
                    }
            }.onFailure {
                sendInfo("&f[ $pluginId &f] Unable to check for update. This may be a problem caused by the network.")
            }
        }
    }
}