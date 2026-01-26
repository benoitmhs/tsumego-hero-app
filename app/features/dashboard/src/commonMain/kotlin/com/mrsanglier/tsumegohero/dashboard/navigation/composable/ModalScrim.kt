package com.mrsanglier.tsumegohero.dashboard.navigation.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun ModalScrim(
    isVisible: Boolean,
    onTap: () -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = THTheme.colors.modal)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { onTap() },
                    )
                }
                .zIndex(1f)
        )
    }
}
