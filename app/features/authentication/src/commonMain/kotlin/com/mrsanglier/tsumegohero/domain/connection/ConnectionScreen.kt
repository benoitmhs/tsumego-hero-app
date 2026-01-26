package com.mrsanglier.tsumegohero.domain.connection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.app.coreui.resources.common_divider_or
import com.mrsanglier.tsumegohero.app.coreui.resources.logo_text
import com.mrsanglier.tsumegohero.coreui.componants.divider.THDividerText
import com.mrsanglier.tsumegohero.coreui.componants.loading.LoadingBackHandler
import com.mrsanglier.tsumegohero.coreui.componants.spacer.THSpacerWeight
import com.mrsanglier.tsumegohero.coreui.componants.textfield.NormalTextUiField
import com.mrsanglier.tsumegohero.coreui.componants.textfielddouble.DoubleTextUiField
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.navigation.safeNavigation
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.resources.THString
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.utils.KeyboardState
import com.mrsanglier.tsumegohero.coreui.utils.rememberKeyboardState
import com.mrsanglier.tsumegohero.domain.choosepassword.ChoosePasswordDestination
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun ConnectionRoute(
    navScope: ConnectionNavScope,
    viewModel: ConnectionViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    safeNavigation(
        navigationState = viewModel.navigation,
        consumeNavigation = viewModel::consumeNavigation,
    ) { navigation ->
        when (navigation) {
            is ConnectionNavigation.Back -> navScope.navigateBack()
            is ConnectionNavigation.ChoosePassword -> {
                navScope.navigateToChoosePassword(ChoosePasswordDestination(navigation.email))
            }
        }
    }

    LoadingBackHandler(
        loadingManager = viewModel.loadingManager,
        enabled = true,
    ) {
        viewModel.backPressed()
    }

    ConnectionScreen(
        uiState = uiState,
        doubleTextUiField = viewModel.doubleTextUiField,
        registerUiText = viewModel.registerTextField,
    )
}

@Composable
private fun ConnectionScreen(
    uiState: ConnectionUiState,
    doubleTextUiField: DoubleTextUiField,
    registerUiText: NormalTextUiField,
) {
    val keyboardState by rememberKeyboardState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(THTheme.colors.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = THTheme.spacing.immense)
                .weight(0.36f),
            contentAlignment = Alignment.Center,
        ) {
            if (keyboardState in setOf(KeyboardState.Hidden, KeyboardState.Disappear)) {
                Image(
                    painter = painterResource(THDrawable.logo_text),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(logoAspectRatio)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.64f)
                .padding(horizontal = THTheme.spacing.huge),
        ) {
            THSpacerWeight(1f)

            // Login section
            AnimatedVisibility(
                visible = uiState.state == ConnectionUiState.State.Login,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
            ) {
                doubleTextUiField.Content(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = THTheme.spacing.large)
                )
            }
            uiState.loginButtonState.Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = THTheme.spacing.large)
            )

            THDividerText(
                text = THString.common_divider_or.toTextSpec(),
                modifier = Modifier.padding(bottom = THTheme.spacing.large)
            )

            // Register section
            AnimatedVisibility(
                visible = uiState.state == ConnectionUiState.State.Register,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
            ) {
                registerUiText.Composable(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = THTheme.spacing.large),
                )
            }
            uiState.registerButtonState.Content(
                modifier = Modifier
                    .fillMaxWidth()
            )

            THSpacerWeight(2f)
        }
    }
}

private val logoAspectRatio: Float = 250f / 68f
