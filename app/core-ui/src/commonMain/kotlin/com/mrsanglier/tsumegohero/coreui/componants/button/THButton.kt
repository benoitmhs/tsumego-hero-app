package com.mrsanglier.tsumegohero.coreui.componants.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.app.coreui.resources.Res
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.ExperimentalCompottieApi
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.dynamic.rememberLottieDynamicProperties
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun THButton(
    text: TextSpec?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    status: ButtonStatus = ButtonStatus.Enabled,
    style: ButtonStyle = ButtonStyle.Primary,
    icon: IconSpec? = null,
    trailingIcon: IconSpec? = null,
    shape: Shape = THTheme.shape.roundMedium,
) {
    val density = LocalDensity.current
    val focusManager = LocalFocusManager.current

    val isLoading = remember(status) { status == ButtonStatus.Loading || status is ButtonStatus.Done }

    val contentColor by animateColorAsState(style.contentColor(status))
    val strokeColor by animateColorAsState(style.strokeColor(status))

    var animationMinWidth by remember { mutableStateOf(Dp.Hairline) }

    Box(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .sizeIn(minHeight = MinHeight)
            .surface(
                shape = shape,
                background = style.background(status),
                borderColor = strokeColor,
                borderWidth = THTheme.stroke.regular,
            )
            .thClickable(
                onClick = {
                    focusManager.clearFocus()
                    onClick()
                },
                enabled = status == ButtonStatus.Enabled,
                role = Role.Button,
                indication = if (status == ButtonStatus.Enabled) ripple(color = contentColor) else null,
            )
            .padding(
                horizontal = THTheme.spacing.large,
            ),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedContent(
            modifier = Modifier.fillMaxWidth(),
            targetState = isLoading,
            transitionSpec = {
                fadeIn(tween(220, delayMillis = 90)).togetherWith(
                    fadeOut(animationSpec = tween(90))
                )
            }
        ) { targetIsLoading ->
            if (targetIsLoading) {
                Box(
                    modifier = Modifier
                        .height(MinHeight)
                        .widthIn(animationMinWidth)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center,
                ) {
                    LoadingView(buttonStatus = status, contentColor = style.loadingColor())
                }
            } else {
                Box(
                    modifier = Modifier
                        .height(MinHeight)
                        .align(Alignment.Center)
                        .onGloballyPositioned {
                            with(density) {
                                animationMinWidth = it.size.width.toDp()
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        icon?.Content(
                            iconSize = IconSize.Medium,
                            tint = contentColor,
                        )
                        THText(
                            text = text,
                            style = THTheme.typography.label200,
                            color = contentColor,
                            maxLines = 1,
                        )
                        trailingIcon?.Content(
                            iconSize = IconSize.Medium,
                            tint = contentColor,
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalCompottieApi::class)
@Composable
private fun LoadingView(
    buttonStatus: ButtonStatus,
    contentColor: Color,
    size: Dp = LoadingSize,
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes("files/anim_done_circle4.lottie")
        )
    }

    if (buttonStatus is ButtonStatus.Done) {
        val progress by animateLottieCompositionAsState(composition)
        Image(
            painter = rememberLottiePainter(
                composition = composition,
                dynamicProperties = rememberLottieDynamicProperties {
                    layer("**", "tik") {
                        shapeLayer("**") {
                            stroke("**") {
                                color { contentColor }
                            }
                        }
                    }
                    layer("**", "circle") {
                        shapeLayer("**") {
                            stroke("**") {
                                color { contentColor }
                            }
                        }
                    }
                },
            ),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
        LaunchedEffect(progress >= 1f) {
            if (progress >= 1f) {
                buttonStatus.onComplete?.invoke()
            }
        }
    } else {
        CircularProgressIndicator(
            color = contentColor,
            strokeWidth = THTheme.stroke.regular,
            strokeCap = StrokeCap.Round,
            modifier = Modifier.size(size),
        )
    }
}

private val MinHeight: Dp = 48.dp
private val LoadingSize: Dp = 32.dp
