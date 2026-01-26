package com.mrsanglier.tsumegohero.coreui.componants.textfielddouble

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_eye
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_eye_close
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.componants.textfield.InputType
import com.mrsanglier.tsumegohero.coreui.componants.textfield.TrailingTextFieldOption
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable

@Stable
data class BasicPasswordTextField(
    override val contentDescription: TextSpec,
    override val leadingIcon: IconSpec? = null,
    override val imeAction: ImeAction = ImeAction.Done,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val placeholder: TextSpec? = null,
    override val label: TextSpec? = null,
    private val initialValue: String? = null,
) : BasicTextUiField(initialValue) {

    override val inputType: InputType = InputType.Password
    override val maxLine: Int = 1

    private var showPassword: Boolean by mutableStateOf(false)

    override val visualTransformation: VisualTransformation
        get() = if (!showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    override val trailingAction: TrailingTextFieldOption.ActionIcon?
        get() = if (getValue().isNotEmpty()) {
            TrailingTextFieldOption.ActionIcon(
                icon = if (showPassword) THDrawable.ic_eye_close.toIconSpec() else THDrawable.ic_eye.toIconSpec(),
                onClick = { showPassword = !showPassword },
            )
        } else null
}