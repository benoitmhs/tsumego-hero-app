package com.mrsanglier.tsumegohero.domain.choosepassword

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import kotlinx.serialization.Serializable

@Serializable
data class ChoosePasswordDestination(
    val email: String,
) : THDestination {

    companion object {
        fun composable(
            navGraphBuilder: NavGraphBuilder,
            navScope: ChoosePasswordNavScope,
        ) {
            navGraphBuilder.composable<ChoosePasswordDestination> {
                ChoosePasswordRoute(navScope)
            }
        }
    }
}
