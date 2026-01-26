package com.mrsanglier.tsumegohero.dashboard.screens.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
object ProfileDestination : THDestination {
    fun composable(
        navGraphBuilder: NavGraphBuilder,
    ) {
        navGraphBuilder.composable<ProfileDestination> {
            ProfileRoute()
        }
    }
}
