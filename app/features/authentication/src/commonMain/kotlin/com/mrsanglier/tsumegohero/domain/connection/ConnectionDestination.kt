package com.mrsanglier.tsumegohero.domain.connection

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
object ConnectionDestination : THDestination {
    fun composable(
        navGraphBuilder: NavGraphBuilder,
        navScope: ConnectionNavScope,
    ) {
        navGraphBuilder.composable<ConnectionDestination> {
            ConnectionRoute(navScope)
        }
    }
}
