package com.mrsanglier.tsumegohero.dashboard.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mrsanglier.tsumegohero.coreui.componants.floatingbutton.FloatingButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.floatingbutton.RoundFloatingButton
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.foClickable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun FabRow(
    icon: IconSpec,
    label: TextSpec,
    style: FloatingButtonStyle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.medium),
        modifier = modifier.foClickable(onClick),
    ) {
        RoundFloatingButton(
            iconSpec = icon,
            style = style,
            onClick = onClick,
        )
        THText(
            text = label,
            color = THTheme.colors.content,
            style = THTheme.typography.label100,
            textAlign = TextAlign.Center
        )
    }
}
