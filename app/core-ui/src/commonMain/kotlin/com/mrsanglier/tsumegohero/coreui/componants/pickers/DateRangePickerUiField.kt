package com.mrsanglier.tsumegohero.coreui.componants.pickers

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_calendar_filled
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.shortFormat
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import kotlinx.datetime.LocalDate

class DateRangePickerUiField(
    override val onClick: () -> Unit,
    override val onValueChange: (LocalDate?) -> Unit = {},
    initialValue: LocalDate? = null,
) : PickerUiField<LocalDate>(initialValue) {
    override val leadingIcon: IconSpec = THDrawable.ic_calendar_filled.toIconSpec()

    @Composable
    override fun LocalDate.getLabel(): TextSpec =
        this.shortFormat().toTextSpec()
}