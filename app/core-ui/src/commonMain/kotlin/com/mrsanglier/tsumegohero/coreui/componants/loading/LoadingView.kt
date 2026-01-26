package com.mrsanglier.tsumegohero.coreui.componants.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

/**
 * Show a loading spinner according to [LoadingManager] state
 *
 * @param loadingContent the actual loading composable to show
 */
@Composable
fun LoadingView(
    loadingManager: LoadingManager,
    contentDescription: TextSpec?,
    modifier: Modifier = Modifier,
    blockingContent: @Composable (() -> Unit) = { DefaultBlockingContent() },
    loadingContent: @Composable () -> Unit = { DefaultLoadingContent(contentDescription?.string) },
) {
    val loadingState by loadingManager.loadingState.collectAsStateWithLifecycle()

    // Blocking back
    BackHandler(enabled = loadingState.isBlocking) {}

    if (loadingState.isBlocking) {
        blockingContent()
    }
    AnimatedLoadingContent(modifier, loadingState.isLoading, loadingContent)
}

@Composable
private fun AnimatedLoadingContent(
    modifier: Modifier,
    showLoading: Boolean,
    loadingContent: @Composable (() -> Unit),
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = showLoading,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        loadingContent()
    }
}

/**
 * A default loading composable to be used in LoadingView
 */
@Composable
fun DefaultLoadingContent(
    loadingContentDescription: String?,
    modifier: Modifier = Modifier,
    backgroundScrim: Color = THTheme.colors.modalSoft,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clearAndSetSemantics {
                loadingContentDescription?.let { loadingContentDescription ->
                    liveRegion = LiveRegionMode.Polite
                    this.contentDescription = loadingContentDescription
                }
            }
            .then(modifier)
            .drawBehind { drawRect(backgroundScrim) },
    ) {
        TripleOrbitLoadingAnimation(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

/**
 * A default blocking composable to be used in LoadingView
 */
@OptIn(ExperimentalComposeUiApi::class)
@Suppress("UnusedReceiverParameter")
@Composable
fun DefaultBlockingContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = {}, enabled = false)
            .semantics {
                this.invisibleToUser()
            },
    )
}
