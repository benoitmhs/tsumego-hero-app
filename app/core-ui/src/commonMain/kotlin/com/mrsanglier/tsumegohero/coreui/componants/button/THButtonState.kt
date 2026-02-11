package com.mrsanglier.tsumegohero.coreui.componants.button

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec

@Immutable
data class THButtonState(
    val text: TextSpec?,
    val onClick: () -> Unit,
    val status: ButtonStatus = ButtonStatus.Enabled,
    val style: ButtonStyle = ButtonStyle.Primary,
    val icon: IconSpec? = null,
    val trailingIcon: IconSpec? = null,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        THButton(
            text = text,
            onClick = onClick,
            modifier = modifier,
            status = status,
            style = style,
            icon = icon,
            trailingIcon = trailingIcon,
        )
    }

    fun lazyItem(
        scope: LazyListScope,
        modifier: Modifier = Modifier,
    ) {
        scope.item(
            contentType = contentType,
            key = text.hashCode(),
        ) {
            Content(modifier)
        }
    }

    fun copy(
        enabled: Boolean,
        text: TextSpec? = this.text,
        onClick: () -> Unit = this.onClick,
        style: ButtonStyle = this.style,
        icon: IconSpec? = this.icon,
        trailingIcon: IconSpec? = this.trailingIcon,
    ): THButtonState = this.copy(
        text = text,
        onClick = onClick,
        status = if (enabled) ButtonStatus.Enabled else ButtonStatus.Disabled,
        style = style,
        icon = icon,
        trailingIcon = trailingIcon,
    )

    companion object Companion {
        private val contentType: String = "PiButton"
    }
}
