package com.mrsanglier.tsumegohero.coreui.componants.textfielddouble

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mrsanglier.tsumegohero.coreui.componants.divider.THDividerHorizontal
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.theme.color.Transparent

@Stable
class DoubleTextUiField(
    val topTextUiField: BasicTextUiField,
    val bottomTextUiField: BasicTextUiField,
    val onValueChange: (String, String) -> Unit,
) {

    private var errorToDisplay: TextSpec? by mutableStateOf(null, referentialEqualityPolicy())

    fun showError(errorText: TextSpec?) {
        errorToDisplay = errorText
    }

    @Composable
    fun Content(modifier: Modifier) {
        val shape = THTheme.shape.roundMedium
        var state by remember { mutableStateOf(State.None) }
        val isError = errorToDisplay != null

        val borderColor: Color by animateColorAsState(
            targetValue = when {
                isError -> THTheme.colors.strokeCritical
                else -> THTheme.colors.strokeElevatedDivider
            }
        )
        val selectorColor by animateColorAsState(if (isError) THTheme.colors.strokeCritical else THTheme.colors.strokeTint)

        Column(modifier = modifier.animateContentSize()) {
            BoxWithConstraints {
                ConstraintLayout(constraintSet = constraints()) {
                    Box(
                        modifier = Modifier
                            .layoutId(BackgroundId)
                            .surface(
                                shape = shape,
                                borderColor = borderColor,
                                borderWidth = THTheme.stroke.thin,
                                background = THTheme.colors.surface1,
                            )
                    )

                    if (state != State.None) {
                        val offset by animateDpAsState(
                            targetValue = if (state == State.Bottom) BasicTextFieldMinHeight else 0.dp
                        )
                        Selector(
                            modifier = Modifier
                                .focusable(false)
                                .offset(y = offset)
                                .height(BasicTextFieldMinHeight),
                            color = selectorColor,
                            shape = shape,
                        )
                    }
                    Column(
                        modifier = Modifier
                            .layoutId(ContentId)
                            .wrapContentHeight(),
                    ) {
                        topTextUiField.Composable(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusEvent { focusState ->
                                    if (focusState.hasFocus) {
                                        state = State.Top
                                    } else if (state == State.Top) {
                                        state = State.None
                                    }
                                },
                            isError = isError,
                            onValueUpdated = {
                                errorToDisplay = null
                                onValueChange(it, bottomTextUiField.getValue())
                            },
                            enabled = true,
                        )
                        if (state == State.None) {
                            THDividerHorizontal(
                                modifier = Modifier.fillMaxWidth(),
                                color = borderColor,
                            )
                        }
                        bottomTextUiField.Composable(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusEvent { focusState ->
                                    if (focusState.hasFocus) {
                                        state = State.Bottom
                                    } else if (state == State.Bottom) {
                                        state = State.None
                                    }
                                },
                            isError = isError,
                            onValueUpdated = {
                                errorToDisplay = null
                                onValueChange(topTextUiField.getValue(), it)
                            },
                            enabled = true,
                        )
                    }
                }
            }
            errorToDisplay?.let { errorText ->
                THText(
                    text = errorText,
                    style = THTheme.typography.content100,
                    color = THTheme.colors.contentCritical,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = THTheme.spacing.tiny,
                            start = THTheme.spacing.medium,
                        ),
                )
            }
        }
    }
}

@Composable
private fun Selector(
    color: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .surface(
                shape = shape,
                background = Transparent,
                borderColor = color,
                borderWidth = THTheme.stroke.regular
            ),
    )
}

private fun constraints() = ConstraintSet {
    val background = createRefFor(BackgroundId)
    val content = createRefFor(ContentId)

    constrain(background) {
        top.linkTo(content.top)
        bottom.linkTo(content.bottom)
        start.linkTo(content.start)
        end.linkTo(content.end)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
}

private enum class State { None, Top, Bottom }

private const val BackgroundId: String = "background.id"
private const val ContentId: String = "content.id"