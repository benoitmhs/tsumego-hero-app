package com.mrsanglier.tsumegohero.data.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class AppVersion(
    val versionName: String,
) : Comparable<AppVersion> {
    override fun compareTo(other: AppVersion): Int {
        val parts1 = this.versionName.split(".", "-").mapNotNull { it.toIntOrNull() }
        val parts2 = other.versionName.split(".", "-").mapNotNull { it.toIntOrNull() }

        val maxLength = maxOf(parts1.size, parts2.size)
        for (i in 0 until maxLength) {
            val p1 = parts1.getOrElse(i) { 0 }
            val p2 = parts2.getOrElse(i) { 0 }

            if (p1 != p2) return p1.compareTo(p2)
        }

        return 0
    }
}
