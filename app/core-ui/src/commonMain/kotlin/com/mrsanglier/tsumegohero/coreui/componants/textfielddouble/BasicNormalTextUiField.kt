package com.mrsanglier.tsumegohero.coreui.componants.textfielddouble

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.componants.textfield.InputType
import com.mrsanglier.tsumegohero.coreui.componants.textfield.TrailingTextFieldOption

@Stable
data class BasicNormalTextUiField(
    override val contentDescription: TextSpec,
    override val leadingIcon: IconSpec? = null,
    override val trailingAction: TrailingTextFieldOption.ActionIcon? = null,
    override val maxLine: Int = 1,
    override val imeAction: ImeAction = ImeAction.Done,
    override val inputType: InputType = InputType.Default,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val placeholder: TextSpec? = null,
    override val label: TextSpec? = null,
    override val visualTransformation: VisualTransformation = VisualTransformation.None,
    private val initialValue: String? = null,
) : BasicTextUiField(initialValue)
