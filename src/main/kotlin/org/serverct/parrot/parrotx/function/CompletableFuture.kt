package org.serverct.parrot.parrotx.function

import taboolib.common.platform.function.submit
import java.util.concurrent.CompletableFuture

fun <E> future(isAsync: Boolean = false, value: () -> E): CompletableFuture<E> {
    return CompletableFuture<E>().completeWith(isAsync, value)
}

fun <E> CompletableFuture<E>.completeWith(isAsync: Boolean, supplier: () -> E): CompletableFuture<E> {
    submit(async = isAsync) {
        runCatching {
            supplier()
        }.onFailure {
            completeExceptionally(it)
        }.onSuccess {
            complete(it)
        }
    }
    return this
}

fun <V> CompletableFuture<*>.step(action: () -> V?): V? {
    return runCatching {
        action()
    }.onFailure {
        completeExceptionally(it)
    }.getOrNull()
}