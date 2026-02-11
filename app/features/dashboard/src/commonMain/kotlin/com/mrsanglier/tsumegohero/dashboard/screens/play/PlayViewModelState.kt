package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.compose.runtime.Immutable
import com.mrsanglier.tsumegohero.dashboardgame.model.TsumegoItem

@Immutable
data class PlayViewModelState(
    val tsugemoItems: List<TsumegoItem> = emptyList(),
)
