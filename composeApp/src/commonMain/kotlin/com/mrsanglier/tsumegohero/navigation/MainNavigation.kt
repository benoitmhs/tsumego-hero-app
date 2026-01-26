package com.mrsanglier.tsumegohero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.mrsanglier.tsumegohero.dashboard.navigation.DashboardDestination
import com.mrsanglier.tsumegohero.dashboard.navigation.DashboardNavScope
import com.mrsanglier.tsumegohero.game.game.GameDestination
import com.mrsanglier.tsumegohero.game.game.GameNavScope
import com.mrsanglier.tsumegohero.navigation.animation.THNavHost
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainNavigation(
    viewModel: MainNavigationViewModel = koinViewModel(),
) {
    val navController = rememberNavController()

    THNavHost(
        navController = navController,
        startDestination = DashboardDestination,
    ) {
        DashboardDestination.composable(
            this,
            DashboardNavScope(
                navigateToGame = { navController.navigate(GameDestination) }
            ),
        )

        GameDestination.composable(
            navGraphBuilder = this,
            navScope = GameNavScope(
                navigateBack = navController::popBackStack,
            )
        )
    }
}
