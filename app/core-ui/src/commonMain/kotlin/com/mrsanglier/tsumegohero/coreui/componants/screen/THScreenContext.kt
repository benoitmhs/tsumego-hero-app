package com.mrsanglier.tsumegohero.coreui.componants.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.chrisbanes.haze.HazeState

val LocalPiScreenPadding: ProvidableCompositionLocal<PaddingValues> = staticCompositionLocalOf { PaddingValues() }
val LocalTopBarHazeState: ProvidableCompositionLocal<HazeState?> = staticCompositionLocalOf { null }

@Composable
fun ProvideScreenContext(
    paddingValues: PaddingValues,
    topBarHazeState: HazeState? = null,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPiScreenPadding provides paddingValues,
        LocalTopBarHazeState provides topBarHazeState,
        content = content,
    )
}
