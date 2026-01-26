package com.mrsanglier.tsumegohero.coreui.componants.textfielddouble

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.componants.textfield.InputType
import com.mrsanglier.tsumegohero.coreui.componants.textfield.TrailingTextFieldOption

abstract class BasicTextUiField(
    initialValue: String?,
) {
    abstract val contentDescription: TextSpec
    abstract val leadingIcon: IconSpec?
    abstract val trailingAction: TrailingTextFieldOption.ActionIcon?
    abstract val maxLine: Int
    abstract val imeAction: ImeAction
    abstract val inputType: InputType
    abstract val keyboardActions: KeyboardActions
    abstract val placeholder: TextSpec?
    abstract val label: TextSpec?
    abstract val visualTransformation: VisualTransformation

    private var rawValue: String by mutableStateOf(initialValue.orEmpty())
    fun getValue(): String = rawValue

    @Composable
    open fun Composable(
        modifier: Modifier,
        enabled: Boolean,
        isError: Boolean,
        onValueUpdated: ((String) -> Unit)?,
    ) {
        THBasicTextField(
            value = rawValue,
            onValueChange = {
                rawValue = it
                onValueUpdated?.invoke(it)
            },
            description = contentDescription,
            label = label,
            placeholder = placeholder,
            visualTransformation = visualTransformation,
            maxLines = maxLine,
            enabled = enabled,
            keyboardOptions = inputType.getKeyboardOption(imeAction),
            keyboardActions = keyboardActions,
            isError = isError,
            leadingIcon = leadingIcon,
            trailingAction = trailingAction,
            modifier = modifier,
        )
    }
}