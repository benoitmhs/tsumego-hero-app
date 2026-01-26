package com.mrsanglier.tsumegohero.coreui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import com.mrsanglier.tsumegohero.core.utils.now
import com.mrsanglier.tsumegohero.core.utils.toDateTime
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn

@OptIn(ExperimentalMaterial3Api::class)
data class FOSelectableDates(
    val minDate: LocalDate? = null,
    val maxDate: LocalDate? = null,
) : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean =
        (minDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds() ?: 0) <= utcTimeMillis
            && (maxDate?.atStartOfDayIn(TimeZone.UTC)?.toEpochMilliseconds() ?: Long.MAX_VALUE) >= utcTimeMillis
}

internal val FutureDates = FOSelectableDates(
    minDate = now().toDateTime().date,
)
