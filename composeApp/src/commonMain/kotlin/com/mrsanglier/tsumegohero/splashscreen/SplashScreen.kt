package com.mrsanglier.tsumegohero.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.loading.DefaultLoadingContent
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(THTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        DefaultLoadingContent(null)
    }
}
