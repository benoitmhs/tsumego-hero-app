package com.mrsanglier.tsumegohero.utils

import com.mrsanglier.tsumegohero.data.model.AppVersion
import platform.Foundation.NSBundle

fun getAppVersion(): AppVersion {
    val info = NSBundle.mainBundle.infoDictionary
    return AppVersion(
        versionName = info?.get("CFBundleShortVersionString") as? String ?: "unknown",
    )
}