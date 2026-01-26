package com.mrsanglier.tsumegohero.coreui.componants.pickers

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_clock
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogManager
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common.TimePickerAlertDialog
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.labelFormat
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import kotlinx.datetime.LocalTime

class TimePickerUiField(
    private val alertDialogManager: AlertDialogManager,
    override val onValueChange: (LocalTime?) -> Unit = {},
    initialValue: LocalTime? = null,
) : PickerUiField<LocalTime>(initialValue) {

    override val leadingIcon: IconSpec = THDrawable.ic_clock.toIconSpec()

    @Composable
    override fun LocalTime.getLabel(): TextSpec = this.labelFormat().toTextSpec()

    override val onClick: () -> Unit
        get() = {
            alertDialogManager.showDialog(
                TimePickerAlertDialog(initialTime = value) { date ->
                    value = date
                    onValueChange(date)
                }
            )
        }
}
