package com.mrsanglier.tsumegohero.navigation.animation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination

@Composable
fun THNavHost(
    navController: NavHostController,
    startDestination: THDestination,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            Transition.fromDestination(targetState.destination.route).enter(this)
        },
        exitTransition = {
            Transition.fromDestination(targetState.destination.route).exit(this)
        },
        popEnterTransition = {
            Transition.fromDestination(initialState.destination.route).popEnter(this)
        },
        popExitTransition = {
            Transition.fromDestination(initialState.destination.route).popExitTransition(this)
        },
        builder = builder,
    )
}
