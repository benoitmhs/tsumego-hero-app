package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
object PlayDestination : THDestination {
    fun composable(
        navGraphBuilder: NavGraphBuilder,
        navScope: PlayNavScope,
    ) {
        navGraphBuilder.composable<PlayDestination> {
            PlayRoute(navScope)
        }
    }
}
