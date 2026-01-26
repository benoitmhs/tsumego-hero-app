package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogState
import kotlinx.datetime.LocalTime

data class TimePickerAlertDialog(
    override val onDismiss: (() -> Unit)? = null,
    private val initialTime: LocalTime? = null,
    val onDatePicked: (LocalTime) -> Unit,
) : AlertDialogState {
    override val actions: List<AlertDialogAction> = emptyList()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(dismiss: () -> Unit) {
        val timePickerState = rememberTimePickerState(
            initialMinute = initialTime?.minute ?: 0,
            initialHour = initialTime?.hour ?: 0,
        )

        THAlertDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                dismiss()
            },
            title = null,
            actions = listOf(
                CommonAlertDialogAction.cancel(),
                CommonAlertDialogAction.validate(
                    onClick = {
                        onDatePicked(
                            LocalTime(hour = timePickerState.hour, minute = timePickerState.minute)
                        )
                    },
                ),
            ),
        ) {
            Box(contentAlignment = Alignment.Center) {
                TimePicker(
                    state = timePickerState,
                    layoutType = TimePickerLayoutType.Vertical,
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                )
            }
        }
    }
}
