package com.mrsanglier.tsumegohero.dashboard.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.screen.LocalPiScreenPadding
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val logoutButton by viewModel.logoutButtonState.collectAsStateWithLifecycle()

    ProfileScreen(
        logoutButton = logoutButton,
    )
}

@Composable
private fun ProfileScreen(
    logoutButton: THButtonState,
) {
    Scaffold(
        backgroundColor = Transparent,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalPiScreenPadding.current)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            logoutButton.Content()
        }
    }
}
