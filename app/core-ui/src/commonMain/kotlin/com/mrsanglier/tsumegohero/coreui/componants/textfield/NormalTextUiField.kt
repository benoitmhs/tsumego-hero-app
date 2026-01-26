package com.mrsanglier.tsumegohero.coreui.componants.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec

@Stable
open class NormalTextUiField(
    initialValue: String? = null,
    override val contentDescription: TextSpec,
    override val title: TextSpec? = null,
    override val leadingIcon: IconSpec? = null,
    override val trailingAction: TrailingTextFieldOption.ActionIcon? = null,
    override val maxLine: Int = 1,
    override val minLines: Int = 1,
    override val imeAction: ImeAction = ImeAction.Done,
    override val inputType: InputType = InputType.Default,
    override val keyboardActions: KeyboardActions = KeyboardActions.Default,
    override val placeholder: TextSpec? = null,
    override val label: TextSpec? = null,
    override val errorToShown: (String) -> TextSpec? = { null },
    override val onValueChanged: ((String) -> Unit)? = null,
) : TextUiField(initialValue) {
    override val visualTransformation: VisualTransformation = VisualTransformation.None
}
