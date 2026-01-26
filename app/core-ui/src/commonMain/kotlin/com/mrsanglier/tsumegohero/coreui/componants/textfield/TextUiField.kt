package com.mrsanglier.tsumegohero.coreui.componants.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec

abstract class TextUiField(initialValue: String?) {

    protected var rawValue: String by mutableStateOf(initialValue.orEmpty())
    abstract val contentDescription: TextSpec
    abstract val visualTransformation: VisualTransformation
    abstract val maxLine: Int
    abstract val minLines: Int
    abstract val inputType: InputType
    abstract val imeAction: ImeAction
    abstract val keyboardActions: KeyboardActions
    abstract val title: TextSpec?
    abstract val label: TextSpec?
    abstract val placeholder: TextSpec?
    abstract val leadingIcon: IconSpec?
    abstract val trailingAction: TrailingTextFieldOption.ActionIcon?
    abstract val errorToShown: (String) -> TextSpec?
    abstract val onValueChanged: ((String) -> Unit)?

    protected var errorToDisplay: TextSpec? by mutableStateOf(null)
        private set

    var enabled: Boolean by mutableStateOf(true)
    var imeActionToDisplay: ImeAction by mutableStateOf(imeAction)

    fun displayErrorOnFieldIfNeeded() {
        displayError(errorToShown(rawValue))
    }

    fun displayError(error: TextSpec?) {
        errorToDisplay = error
    }

    fun isValid(): Boolean = errorToShown(rawValue) == null

    fun updateValue(value: String) {
        rawValue = value
        displayError(null)
        onValueChanged?.invoke(value)
    }

    open fun getValue(): String = rawValue

    // Boolean to display error only if the field was in focus
    private var wasCaptured: Boolean = false
    open fun onFocusEvent(focusState: FocusState) {
        if (wasCaptured) {
            wasCaptured = false
            displayErrorOnFieldIfNeeded()
        } else if (focusState.hasFocus && focusState.isFocused) {
            wasCaptured = true
        }
    }

    @Composable
    open fun Composable(
        modifier: Modifier,
    ) {
        THTextField(
            value = rawValue,
            description = contentDescription,
            title = title,
            label = label,
            placeholder = placeholder,
            onValueChange = ::updateValue,
            visualTransformation = visualTransformation,
            maxLines = maxLine,
            enabled = enabled,
            keyboardOptions = inputType.getKeyboardOption(imeActionToDisplay),
            keyboardActions = keyboardActions,
            errorText = errorToDisplay,
            leadingIcon = leadingIcon,
            trailingAction = trailingAction,
            modifier = modifier
                .fillMaxWidth()
                .onFocusEvent(::onFocusEvent),
        )
    }
}
