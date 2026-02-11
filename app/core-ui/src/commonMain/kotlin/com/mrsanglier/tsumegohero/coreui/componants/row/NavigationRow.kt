package com.mrsanglier.tsumegohero.coreui.componants.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_arrow_forward
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun NavigationRow(
    label: TextSpec,
    icon: IconSpec?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = MinHeight)
            .thClickable(onClick)
            .padding(
                horizontal = THTheme.spacing.large,
                vertical = THTheme.spacing.small,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
    ) {
        icon?.Content(
            iconSize = IconSize.Regular,
            tint = THTheme.colors.content,
        )

        THText(
            text = label,
            color = THTheme.colors.content,
            style = THTheme.typography.content200,
            modifier = Modifier.weight(1f),
        )

        THIcon(
            icon = THDrawable.ic_arrow_forward.toIconSpec(),
            iconSize = IconSize.Small,
            tint = THTheme.colors.contentSecondary,
        )
    }
}

private val MinHeight: Dp = 56.dp