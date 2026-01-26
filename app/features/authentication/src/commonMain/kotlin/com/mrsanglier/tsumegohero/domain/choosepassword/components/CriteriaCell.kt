package com.mrsanglier.tsumegohero.domain.choosepassword.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_circle_yes_filled
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_wrong
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun CriteriaCell(
    valid: Boolean,
    text: TextSpec,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
    ) {

        THIcon(
            icon = (if (valid) THDrawable.ic_circle_yes_filled else THDrawable.ic_wrong).toIconSpec(),
            iconSize = IconSize.Small,
            tint = if (valid) THTheme.colors.content else THTheme.colors.contentSecondary,
        )

        THText(
            text = text,
            style = THTheme.typography.content50,
        )
    }
}

@Stable
data class CriteriaCellState(
    val valid: Boolean,
    val text: TextSpec,
) {
    private companion object {
        const val contentType: String = "CriteriaCell"
    }

    @Composable
    fun Composable(modifier: Modifier = Modifier) {
        CriteriaCell(
            valid = valid,
            text = text,
            modifier = modifier,
        )
    }

    fun item(
        modifier: Modifier = Modifier,
        lazyGridScope: LazyGridScope,
    ) {
        lazyGridScope.item(
            key = text.hashCode(),
            contentType = contentType,
        ) {
            Composable(modifier)
        }
    }
}
