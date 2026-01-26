package com.mrsanglier.tsumegohero.coreui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStatus
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButton
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import androidx.compose.ui.tooling.preview.Preview
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_add
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_add_event
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewTHButton() {
    THTheme {
        Column(
            modifier = Modifier.background(THTheme.colors.background),
            verticalArrangement = Arrangement.spacedBy(THTheme.spacing.medium),
        ) {
            listOf(
                ButtonStyle.Primary,
                ButtonStyle.Outlined,
                ButtonStyle.Text,
                ButtonStyle.Critical,
            ).forEach { style ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.medium),
                ) {
                    listOf(ButtonStatus.Enabled, ButtonStatus.Disabled).forEach { state ->
                        THButton(
                            text = "Button".toTextSpec(),
                            icon = THDrawable.ic_add_event.toIconSpec(),
                            style = style,
                            status = state,
                            onClick = {},
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoadingButton() {
    THTheme {
        var buttonStatus: ButtonStatus by remember { mutableStateOf(ButtonStatus.Enabled) }
        val coroutineScope = rememberCoroutineScope()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(THTheme.colors.background)
                .padding(THTheme.spacing.large),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(THTheme.spacing.large),
            ) {
                THButton(
                    text = "Button".toTextSpec(),
                    icon = THDrawable.ic_add.toIconSpec(),
                    status = buttonStatus,
                    onClick = {
                        coroutineScope.launch {
                            buttonStatus = ButtonStatus.Loading
                            delay(2000)
                            buttonStatus = ButtonStatus.Done {
                                buttonStatus = ButtonStatus.Enabled
                            }
                        }
                    },
                    //                modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}