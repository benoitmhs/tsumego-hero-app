package com.mrsanglier.tsumegohero.domain.common

import android.os.LocaleList

actual fun getPreferredLanguageCodes(): List<String> =
    LocaleList.getDefault().let { localeList ->
        (0 until localeList.size()).map { localeList[it].language }
    }
