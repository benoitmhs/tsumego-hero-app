package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.views.ErrorSnackbar
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.views.ShortSnackbar
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun SnackbarView(
    viewModel: SnackbarViewModel,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val snackbarState by viewModel.shownSnackBar.collectAsStateWithLifecycle()
    val snackbarVisuals = snackbarState?.toSnackbarVisuals()

    LaunchedEffect(snackbarVisuals) {
        snackbarVisuals?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.consumeSnackBar()
        }
    }
    Box(
        modifier = modifier.systemBarsPadding(),
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
        ) { data ->
            val piVisual = data.visuals.toFOData()
            when (piVisual) {
                is THSnackbarVisual.Short -> {
                    ShortSnackbar(
                        snackbarVisual = piVisual,
                        dismiss = data::dismiss,
                        modifier = Modifier.padding(THTheme.spacing.large),
                    )
                }

                is THSnackbarVisual.Default -> {
                    // TODO DefaultSnackbar view
                }

                is THSnackbarVisual.Error -> {
                    ErrorSnackbar(
                        snackbarVisual = piVisual,
                        dismiss = data::dismiss,
                        modifier = Modifier.padding(THTheme.spacing.large),
                    )
                }
            }
        }
    }
}