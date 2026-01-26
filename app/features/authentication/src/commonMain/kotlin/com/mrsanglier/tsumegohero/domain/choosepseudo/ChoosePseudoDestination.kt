package com.mrsanglier.tsumegohero.domain.choosepseudo

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
data class ChoosePseudoDestination(
    val email: String,
    val password: String,
) : THDestination {

    companion object {
        fun composable(
            navGraphBuilder: NavGraphBuilder,
            navScope: ChoosePseudoNavScope,
        ) {
            navGraphBuilder.composable<ChoosePseudoDestination> {
                ChoosePseudoRoute(navScope)
            }
        }
    }
}
