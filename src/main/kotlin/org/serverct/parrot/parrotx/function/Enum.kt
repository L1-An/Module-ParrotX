@file:Suppress("unused")

package org.serverct.parrot.parrotx.function

import com.google.common.base.Enums

inline fun <reified T : Enum<T>> String?.enumOf(transfer: (String) -> String = { it.uppercase() }): T? {
    return if (this == null) null else Enums.getIfPresent(T::class.java, transfer(this)).orNull()
}

inline fun <reified E : Enum<E>> E.next(): E = enumValues<E>().let { it[ordinal.next(it.indices)] }

inline fun <reified E : Enum<E>> E.last(): E = enumValues<E>().let { it[ordinal.last(it.indices)] }