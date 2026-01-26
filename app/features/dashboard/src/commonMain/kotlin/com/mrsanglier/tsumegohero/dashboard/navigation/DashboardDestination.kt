package com.mrsanglier.tsumegohero.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
object DashboardDestination : THDestination {
    fun composable(
        navGraphBuilder: NavGraphBuilder,
        navScope: DashboardNavScope,
    ) {
        navGraphBuilder.composable<DashboardDestination> {
            DashboardRoute(navScope)
        }
    }
}
