package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButton
import com.mrsanglier.tsumegohero.coreui.componants.screen.LocalPiScreenPadding
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import dev.chrisbanes.haze.HazeState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlayRoute(
    navScope: PlayNavScope,
    viewModel: PlayViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PlayScreen(
        uiState = uiState,
        navigateToGame = navScope.navigateToGame,
    )
}

@Composable
private fun PlayScreen(
    uiState: PlayViewModelState,
    navigateToGame: () -> Unit,
) {
    val hazeState = remember { HazeState() }

    Scaffold(
        backgroundColor = Transparent,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(LocalPiScreenPadding.current)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            THButton(
                text = "Play".toTextSpec(), // TODO: loco
                onClick = navigateToGame,
            )
        }
    }
}

private val LogoHeight: Dp = 24.dp
private val logoAspectRatio: Float = 250f / 68f
