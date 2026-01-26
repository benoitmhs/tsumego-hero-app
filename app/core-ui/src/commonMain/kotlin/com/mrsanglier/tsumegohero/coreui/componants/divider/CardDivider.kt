package com.mrsanglier.tsumegohero.coreui.componants.divider

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun CardDivider() {
    THDividerHorizontal(
        color = THTheme.colors.strokeDivider,
        modifier = Modifier.padding(horizontal = THTheme.spacing.large),
    )
}
