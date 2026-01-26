package com.mrsanglier.tsumegohero.domain.common

expect fun getPreferredLanguageCodes(): List<String>

fun getSupportedLanguage(): String =
    getPreferredLanguageCodes().firstOrNull { lang ->
        supportedLanguage.contains(lang)
    } ?: defaultLanguage

val supportedLanguage: Set<String> = setOf("fr", "en")
const val defaultLanguage: String = "en"
