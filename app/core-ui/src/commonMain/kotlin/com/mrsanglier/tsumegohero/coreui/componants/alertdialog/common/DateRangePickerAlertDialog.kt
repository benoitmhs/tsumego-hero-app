package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogState
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

data class DateRangePickerAlertDialog @OptIn(ExperimentalMaterial3Api::class) constructor(
    override val onDismiss: (() -> Unit)? = null,
    private val initialStartDate: LocalDate? = null,
    private val initialEndDate: LocalDate? = null,
    val onDatesPicked: (LocalDate?, LocalDate?) -> Unit,
    private val selectableDates: SelectableDates = DatePickerDefaults.AllDates,
) : AlertDialogState {
    override val actions: List<AlertDialogAction> = emptyList()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(dismiss: () -> Unit) {
        val dateRangePickerState = rememberDateRangePickerState(
            initialSelectedStartDateMillis = initialStartDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds(),
            initialSelectedEndDateMillis = initialEndDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds()
                .takeIf { initialStartDate != null },
            selectableDates = selectableDates,
        )

        val confirmEnabled by remember {
            derivedStateOf { dateRangePickerState.selectedStartDateMillis != null }
        }

        DatePickerDialog(
            tonalElevation = 0.dp,
            onDismissRequest = {
                onDismiss?.invoke()
                dismiss()
            },
            confirmButton = {
                CommonAlertDialogAction.validate(
                    enabled = confirmEnabled,
                    onClick = {
                        val startDate = dateRangePickerState.selectedStartDateMillis
                            ?.let(Instant::fromEpochMilliseconds)
                            ?.toLocalDateTime(TimeZone.UTC)?.date
                        val endDate = dateRangePickerState.selectedEndDateMillis
                            ?.let(Instant::fromEpochMilliseconds)
                            ?.toLocalDateTime(TimeZone.UTC)?.date
                        onDatesPicked(startDate, endDate)
                    }
                ).Content(dismiss)
            },
            dismissButton =
            {
                CommonAlertDialogAction.cancel().Content(dismiss)
            },
            colors = FODatePickersColors,
        )
        {
            DateRangePicker(
                state = dateRangePickerState,
                colors = FODatePickersColors,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private val FODatePickersColors: DatePickerColors
    @Composable
    get() = DatePickerDefaults.colors(
        containerColor = THTheme.colors.surface1,
        titleContentColor = THTheme.colors.content,
        headlineContentColor = THTheme.colors.content,
        weekdayContentColor = THTheme.colors.content,
        subheadContentColor = THTheme.colors.content,
        navigationContentColor = THTheme.colors.content,
        yearContentColor = THTheme.colors.contentSecondary,
        disabledYearContentColor = THTheme.colors.contentDisable,
        currentYearContentColor = THTheme.colors.content,
        selectedYearContentColor = THTheme.colors.contentTint,
        disabledSelectedYearContentColor = THTheme.colors.contentDisable,
        selectedYearContainerColor = THTheme.colors.surface1,
        disabledSelectedYearContainerColor = THTheme.colors.surface1,
        dayContentColor = THTheme.colors.content,
        disabledDayContentColor = THTheme.colors.contentDisable,
        selectedDayContentColor = THTheme.colors.contentOnSurfaceTint,
        disabledSelectedDayContentColor = THTheme.colors.contentDisable,
        selectedDayContainerColor = THTheme.colors.surfaceAccent,
        disabledSelectedDayContainerColor = THTheme.colors.contentDisable,
        todayContentColor = THTheme.colors.content,
        todayDateBorderColor = THTheme.colors.content,
        dayInSelectionRangeContentColor = THTheme.colors.content,
        dayInSelectionRangeContainerColor = THTheme.colors.surfaceFocus,
        dividerColor = THTheme.colors.strokeDivider,
    )

