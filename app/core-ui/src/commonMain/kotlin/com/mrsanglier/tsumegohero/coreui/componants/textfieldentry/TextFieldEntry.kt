@file:OptIn(ExperimentalUuidApi::class)

package com.mrsanglier.tsumegohero.coreui.componants.textfieldentry

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_close
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.componants.textfield.THTextField
import com.mrsanglier.tsumegohero.coreui.componants.textfield.TrailingTextFieldOption
import com.mrsanglier.tsumegohero.coreui.componants.textfield.thOutlinedTextFieldColors
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Immutable
data class TextFieldEntry(
    val id: String = Uuid.random().toString(),
    val value: String,
    val onValueChange: (String) -> Unit,
    val description: TextSpec,
    val error: TextSpec? = null,
    val onDelete: (() -> Unit)? = null,
    val placeholder: TextSpec? = null,
)

@Composable
fun TextFieldEntry.BasicTextField(modifier: Modifier = Modifier) {
    THTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        description = description,
        placeholder = placeholder,
        trailingAction = onDelete?.let {
            TrailingTextFieldOption.ActionIcon(
                onClick = onDelete,
                icon = THDrawable.ic_close.toIconSpec(),
            )
        },
        textStyle = THTheme.typography.label200,
        maxLines = 1,
        errorText = error,
        colors = thOutlinedTextFieldColors(),
    )
}
