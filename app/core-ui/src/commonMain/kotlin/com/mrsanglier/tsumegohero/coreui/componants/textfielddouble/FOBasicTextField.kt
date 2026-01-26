package com.mrsanglier.tsumegohero.coreui.componants.textfielddouble

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.componants.iconbutton.FOIconButton
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.componants.textfield.TrailingTextFieldOption
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.utils.AnimatedNullableVisibility

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun THBasicTextField(
    value: String?,
    onValueChange: (String) -> Unit,
    description: TextSpec,
    modifier: Modifier = Modifier,
    label: TextSpec? = null,
    placeholder: TextSpec? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: IconSpec? = null,
    trailingAction: TrailingTextFieldOption.ActionIcon? = null,
    kbController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(
        onDone = {
            kbController?.hide()
            focusManager.clearFocus()
        },
    ),
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    singleLine: Boolean = maxLines == 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = THTheme.shape.textField,
    style: TextStyle = THTheme.typography.content200,
    contentPadding: PaddingValues = if (label?.string == null) TextFieldDefaultPadding else TextFieldLabelPadding,
) {
    val colors = TextFieldDefaults.textFieldColors(
        textColor = THTheme.colors.content,
        disabledTextColor = THTheme.colors.contentDisable,
        backgroundColor = Color.Transparent,
        cursorColor = THTheme.colors.contentTint,
        errorCursorColor = THTheme.colors.contentCritical,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = THTheme.colors.contentCritical,
        leadingIconColor = THTheme.colors.content,
        disabledLeadingIconColor = THTheme.colors.contentDisable,
        errorLeadingIconColor = THTheme.colors.contentCritical,
        trailingIconColor = THTheme.colors.content,
        disabledTrailingIconColor = THTheme.colors.contentDisable,
        errorTrailingIconColor = THTheme.colors.contentCritical,
        focusedLabelColor = THTheme.colors.contentTint,
        unfocusedLabelColor = THTheme.colors.contentSecondary,
        disabledLabelColor = THTheme.colors.contentDisable,
        errorLabelColor = THTheme.colors.contentCritical,
        placeholderColor = THTheme.colors.contentSecondary,
        disabledPlaceholderColor = THTheme.colors.contentDisable,
    )
    // If color is not provided via the text style, use content color as a default
    val textColor = style.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = style.merge(TextStyle(color = textColor))
    val contentDescription = description.string

    BasicTextField(
        value = value.orEmpty(),
        modifier = modifier
            .background(colors.backgroundColor(enabled).value, shape)
            .indicatorLine(enabled, isError, interactionSource, colors)
            // Override min size()
            .defaultMinSize(
                minWidth = TextFieldMinWidth,
                minHeight = BasicTextFieldMinHeight,
            )
            .semantics {
                this.contentDescription = contentDescription
            },
        onValueChange = onValueChange,
        enabled = enabled,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.TextFieldDecorationBox(
                value = value.orEmpty(),
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                label = label?.let {
                    {
                        THText(
                            text = label,
                            style = LocalTextStyle.current.merge(fontFamily = THTheme.typography.content200.fontFamily),
                            color = Color.Unspecified,
                        )
                    }
                },
                placeholder = placeholder?.let {
                    {
                        THText(
                            text = placeholder,
                            style = THTheme.typography.content200,
                            color = Color.Unspecified,
                        )
                    }
                },
                leadingIcon = leadingIcon?.let {
                    {
                        THIcon(
                            icon = leadingIcon,
                            iconSize = IconSize.Regular,
                            tint = Color.Unspecified,
                        )
                    }
                },
                trailingIcon = {
                    AnimatedNullableVisibility(
                        value = trailingAction,
                    ) { action ->
                        FOIconButton(
                            icon = action.icon,
                            onClick = action.onClick,
                        )
                    }
                },
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors,
                contentPadding = contentPadding,
            )
        }
    )
}

internal val BasicTextFieldMinHeight: Dp = 56.dp
private val TextFieldMinWidth: Dp = 180.dp

private val horizontalTextFieldDefault: Dp = 12.dp
private val topTextFieldDefault: Dp = 8.dp
private val topTextFieldLabel: Dp = 16.dp
private val bottomTextFieldDefault: Dp = 4.dp

private val TextFieldDefaultPadding: PaddingValues = PaddingValues(
    start = horizontalTextFieldDefault,
    end = horizontalTextFieldDefault,
    top = topTextFieldDefault,
    bottom = bottomTextFieldDefault,
)
val TextFieldLabelPadding: PaddingValues = PaddingValues(
    start = horizontalTextFieldDefault,
    end = horizontalTextFieldDefault,
    top = topTextFieldLabel,
    bottom = bottomTextFieldDefault,
)