package com.mrsanglier.tsumegohero.utils

import android.content.Context
import com.mrsanglier.tsumegohero.data.model.AppVersion

fun getAndroidAppVersion(context: Context): AppVersion {
    val info = context.packageManager.getPackageInfo(context.packageName, 0)

    return AppVersion(
        versionName = info.versionName ?: "unknown",
    )
}
