package com.mrsanglier.tsumegohero.data.model

data class AppConfig(
    val environment: Environment,
) {

    enum class Environment(private val value: String) {
        Dev("dev"),
        Prod("prod"),
        ;

        companion object {
            fun buildFromString(value: String): Environment =
                Environment.entries.first { it.value == value }
        }
    }
}