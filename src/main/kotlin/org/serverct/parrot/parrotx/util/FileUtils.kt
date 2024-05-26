@file:Suppress("unused")

package org.serverct.parrot.parrotx.util

import taboolib.common.platform.function.getDataFolder
import java.io.File

/**
 * 传入 path，对path内的每个文件执行 callback
 */
fun files(path : String, callback : (File) -> Unit) {
    val file = File(getDataFolder(), path)
    getFiles(file).forEach { callback(it) }
}

/**
 * 获取对应路径下所有包括深层的文件
 */
fun getFiles(file: File): List<File> {
    val fileList = mutableListOf<File>()
    when (file.isDirectory) {
        true -> fileList += file.listFiles()!!.flatMap { getFiles(it) }
        false -> {
            if (file.name.endsWith(".yml", true)) {
                fileList += file
            }
        }
    }
    return fileList
}