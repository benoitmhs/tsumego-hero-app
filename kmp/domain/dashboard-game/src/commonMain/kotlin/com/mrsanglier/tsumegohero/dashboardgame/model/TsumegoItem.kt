package com.mrsanglier.tsumegohero.dashboardgame.model

import kotlin.time.Instant

data class TsumegoItem(
    val title: String,
    val tsumegoId: String,
    val updatedAt: Instant,
)
