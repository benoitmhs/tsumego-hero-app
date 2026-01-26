package com.mrsanglier.tsumegohero.coreui.componants.loading.loader

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun FOLoading(
    loadingSize: LoadingSize,
    modifier: Modifier = Modifier,
    color: Color = THTheme.colors.contentTint,
) {
    CircularProgressIndicator(
        modifier = modifier.size(loadingSize.size),
        color = color,
        strokeCap = StrokeCap.Round,
    )
}

enum class LoadingSize(
    val size: Dp,
    val stroke: Dp
) {
    Small(16.dp, 3.dp);
}