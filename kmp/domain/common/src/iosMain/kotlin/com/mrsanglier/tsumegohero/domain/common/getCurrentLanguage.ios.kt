package com.mrsanglier.tsumegohero.domain.common

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleLanguageCode
import platform.Foundation.preferredLanguages

actual fun getPreferredLanguageCodes(): List<String> {
    return NSLocale.preferredLanguages.mapNotNull { localeId ->
        val locale = NSLocale(localeId as String)
        locale.objectForKey(NSLocaleLanguageCode) as? String
    }
}
