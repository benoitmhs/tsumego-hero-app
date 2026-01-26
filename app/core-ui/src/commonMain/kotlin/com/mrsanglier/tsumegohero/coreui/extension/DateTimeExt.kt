package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_friday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_monday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_saturday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_sunday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_thursday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_tuesday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_dayOfWeekShort_wednesday
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_april
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_august
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_december
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_february
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_january
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_july
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_june
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_march
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_may
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_november
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_october
import com.mrsanglier.tsumegohero.app.coreui.resources.common_monthShort_september
import com.mrsanglier.tsumegohero.core.utils.minDigitNumber
import com.mrsanglier.tsumegohero.core.utils.now
import com.mrsanglier.tsumegohero.core.utils.toDateTime
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.Month

fun DayOfWeek.shortText(): TextSpec = when (this) {
    DayOfWeek.MONDAY -> THString.common_dayOfWeekShort_monday
    DayOfWeek.TUESDAY -> THString.common_dayOfWeekShort_tuesday
    DayOfWeek.WEDNESDAY -> THString.common_dayOfWeekShort_wednesday
    DayOfWeek.THURSDAY -> THString.common_dayOfWeekShort_thursday
    DayOfWeek.FRIDAY -> THString.common_dayOfWeekShort_friday
    DayOfWeek.SATURDAY -> THString.common_dayOfWeekShort_saturday
    DayOfWeek.SUNDAY -> THString.common_dayOfWeekShort_sunday
    else -> error("Invalid day of week")
}.toTextSpec()

fun Month.shortText(): TextSpec = when (this) {
    Month.JANUARY -> THString.common_monthShort_january
    Month.FEBRUARY -> THString.common_monthShort_february
    Month.MARCH -> THString.common_monthShort_march
    Month.APRIL -> THString.common_monthShort_april
    Month.MAY -> THString.common_monthShort_may
    Month.JUNE -> THString.common_monthShort_june
    Month.JULY -> THString.common_monthShort_july
    Month.AUGUST -> THString.common_monthShort_august
    Month.SEPTEMBER -> THString.common_monthShort_september
    Month.OCTOBER -> THString.common_monthShort_october
    Month.NOVEMBER -> THString.common_monthShort_november
    Month.DECEMBER -> THString.common_monthShort_december
    else -> error("Invalid month")
}.toTextSpec()

@Composable
fun LocalDate.shortFormat(): String =
    listOfNotNull(
        dayOfWeek.shortText().string,
        dayOfMonth.toString(),
        month.shortText().string,
        year.takeIf { now().toDateTime().year != year }?.toString(),
    ).joinToString(" ")

fun LocalTime.labelFormat(): String =
    "${this.hour.minDigitNumber(2)} : ${this.minute.minDigitNumber(2)}"
