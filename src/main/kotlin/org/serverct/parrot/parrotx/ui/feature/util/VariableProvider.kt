package org.serverct.parrot.parrotx.ui.feature.util

import org.serverct.parrot.parrotx.ui.data.ActionContext

interface VariableProvider {

    val name: String

    fun produce(context: ActionContext): String

    operator fun invoke(context: ActionContext): String = produce(context)

}

@Suppress("unused")
class VariableProviderBuilder(name: String? = null, builder: VariableProviderBuilder.() -> Unit) : VariableProvider {

    override var name: String = name ?: ""
        internal set

    private var producer: (ActionContext) -> String = { "%${name}%" }

    init {
        builder()
    }

    fun onProduce(block: (ActionContext) -> String) {
        producer = block
    }

    override fun produce(context: ActionContext): String = producer(context)

}