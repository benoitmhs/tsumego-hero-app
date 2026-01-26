package com.mrsanglier.tsumegohero.coreui.componants.pickers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec

abstract class PickerUiField<T>(
    initialValue: T?,
) {
    var value: T? by mutableStateOf(initialValue)

    abstract val onValueChange: (T?) -> Unit
    abstract val leadingIcon: IconSpec
    abstract val onClick: () -> Unit

    @Composable
    abstract fun T.getLabel(): TextSpec

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
    ) {
        PickerCell(
            label = value?.getLabel() ?: "-".toTextSpec(),
            modifier = modifier,
            leadingIcon = leadingIcon,
            onClick = onClick,
            enabled = enabled,
            onClose = {
                value = null
                onValueChange(null)
            }.takeIf { value != null },
        )
    }
}