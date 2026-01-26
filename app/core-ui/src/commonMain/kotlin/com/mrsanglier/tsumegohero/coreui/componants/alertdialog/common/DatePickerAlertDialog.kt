package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogState
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

data class DatePickerAlertDialog(
    override val onDismiss: (() -> Unit)? = null,
    private val initialDate: LocalDate? = null,
    val onDatePicked: (LocalDate) -> Unit,
) : AlertDialogState {
    override val actions: List<AlertDialogAction> = emptyList()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(dismiss: () -> Unit) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = initialDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds(),
        )
        val confirmEnabled by remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }

        DatePickerDialog(
            onDismissRequest = {
                onDismiss?.invoke()
                dismiss()
            },
            confirmButton = {
                CommonAlertDialogAction.validate(
                    enabled = confirmEnabled,
                    onClick = {
                        datePickerState.selectedDateMillis?.let(Instant::fromEpochMilliseconds)?.let { instant ->
                            onDatePicked(instant.toLocalDateTime(TimeZone.UTC).date)
                        }
                    },
                ).Content(dismiss)
            },
            dismissButton = {
                CommonAlertDialogAction.cancel().Content(dismiss)
            },
        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.verticalScroll(rememberScrollState()),
            )
        }
    }
}
