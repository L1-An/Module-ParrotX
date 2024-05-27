package org.serverct.parrot.parrotx.update

import com.google.gson.JsonParser
import taboolib.common.platform.function.submitAsync
import java.net.URL
import java.util.function.Consumer

@Suppress("unused")
/**
 * 基于 Github API 检测 release 的更新检查器
 * 使用方法：
 * ```kotlin
 * GithubUpdateChecker("l1-an", "YuSpawnerHologram").getVersion {
 *     if (pluginVersion == it) {
 *         sendInfo(&f[ $pluginId &f] There is not a new update available.)
 *     } else {
 *         sendInfo {
 *             +"&f[ $pluginId &f] There is a new update available at:"
 *             +"https://github.com/L1-An/YuSpawnerHologram/releases/latest"
 *         }
 *     }
 * }
 * ```
 */
class GithubUpdateChecker(private val author: String, private val githubRepository: String) {
    fun getVersion(consumer: Consumer<String>) {
        submitAsync {
            runCatching {
                val jsonStr = URL("https://api.github.com/repos/$author/$githubRepository/releases/latest").readText()
                val jsonObj = JsonParser.parseString(jsonStr).asJsonObject
                val version = jsonObj["tag_name"].asString

                consumer.accept(version)
            }
        }
    }
}