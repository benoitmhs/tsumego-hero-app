package com.mrsanglier.tsumegohero.domain.choosepseudo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.app.coreui.resources.choosePseudo_topbar_title
import com.mrsanglier.tsumegohero.app.coreui.resources.illustration_pseudo
import com.mrsanglier.tsumegohero.coreui.componants.bottombar.THBottomBar
import com.mrsanglier.tsumegohero.coreui.componants.textfield.NormalTextUiField
import com.mrsanglier.tsumegohero.coreui.componants.topbar.THTopBar
import com.mrsanglier.tsumegohero.coreui.componants.topbar.TopBarAction
import com.mrsanglier.tsumegohero.coreui.extension.rememberBottomBarElevation
import com.mrsanglier.tsumegohero.coreui.extension.rememberTopBarElevation
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.resources.THString
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.utils.KeyboardState
import com.mrsanglier.tsumegohero.coreui.utils.rememberKeyboardState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeSource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChoosePseudoRoute(
    navScope: ChoosePseudoNavScope,
    viewModel: ChoosePseudoViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChoosePseudoScreen(
        uiState = uiState,
        navigateBack = navScope.navigateBack,
        pseudoTextUIField = viewModel.pseudoTextField,
    )
}

@Composable
private fun ChoosePseudoScreen(
    uiState: ChoosePseudoViewModelState,
    navigateBack: () -> Unit,
    pseudoTextUIField: NormalTextUiField,
) {
    val scrollState = rememberScrollState()
    val topBarHazeState = remember { HazeState() }
    val bottomBarHazeState = remember { HazeState() }
    val topBarElevation by rememberTopBarElevation(scrollState)
    val bottomBarElevation by rememberBottomBarElevation(scrollState)

    val keyboardState by rememberKeyboardState()

    LaunchedEffect(keyboardState) {
        if (keyboardState == KeyboardState.Shown) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        containerColor = THTheme.colors.background,
        topBar = {
            THTopBar(
                title = THString.choosePseudo_topbar_title.toTextSpec(),
                hazeState = topBarHazeState,
                navAction = TopBarAction.back(navigateBack),
                elevation = topBarElevation,
            )
        },
        bottomBar = {
            THBottomBar(
                hazeState = bottomBarHazeState,
                elevation = bottomBarElevation,
                primaryButton = uiState.button,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .hazeSource(topBarHazeState)
                .hazeSource(bottomBarHazeState)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(THDrawable.illustration_pseudo),
                contentDescription = null,
                modifier = Modifier.size(IllustrationSize),
            )

            Column(
                modifier = Modifier.padding(THTheme.spacing.large),
                verticalArrangement = Arrangement.spacedBy(THTheme.spacing.large),
            ) {
                pseudoTextUIField.Composable(Modifier)
            }
        }
    }
}

private val IllustrationSize: Dp = 300.dp
