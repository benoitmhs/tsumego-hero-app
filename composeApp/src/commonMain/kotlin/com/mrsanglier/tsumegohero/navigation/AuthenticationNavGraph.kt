package com.mrsanglier.tsumegohero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.mrsanglier.tsumegohero.domain.choosepassword.ChoosePasswordDestination
import com.mrsanglier.tsumegohero.domain.choosepassword.ChoosePasswordNavScope
import com.mrsanglier.tsumegohero.domain.choosepseudo.ChoosePseudoDestination
import com.mrsanglier.tsumegohero.domain.choosepseudo.ChoosePseudoNavScope
import com.mrsanglier.tsumegohero.domain.connection.ConnectionDestination
import com.mrsanglier.tsumegohero.domain.connection.ConnectionNavScope
import com.mrsanglier.tsumegohero.navigation.animation.THNavHost

@Composable
fun AuthenticationNavigation() {
    val navController = rememberNavController()

    THNavHost(
        navController = navController,
        startDestination = ConnectionDestination,
    ) {
        ConnectionDestination.composable(
            navGraphBuilder = this,
            navScope = ConnectionNavScope(
                navigateBack = navController::popBackStack,
                navigateToChoosePassword = navController::navigate,
            ),
        )

        ChoosePasswordDestination.composable(
            navGraphBuilder = this,
            navScope = ChoosePasswordNavScope(
                navigateBack = navController::popBackStack,
                navigateToChoosePseudo = navController::navigate,
            ),
        )

        ChoosePseudoDestination.composable(
            navGraphBuilder = this,
            navScope = ChoosePseudoNavScope(
                navigateBack = navController::popBackStack,
            ),
        )
    }
}
