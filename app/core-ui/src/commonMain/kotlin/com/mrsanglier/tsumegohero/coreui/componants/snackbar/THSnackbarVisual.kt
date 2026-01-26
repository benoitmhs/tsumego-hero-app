package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import androidx.compose.material3.SnackbarVisuals
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_check
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed interface THSnackbarVisual {

    @Serializable
    @SerialName("SnackbarShort")
    data class Short(
        val text: String,
        val icon: SnackBarIcon?,
    ) : THSnackbarVisual

    @Serializable
    @SerialName("SnackbarDefault")
    data class Default(
        val text: String,
    ) : THSnackbarVisual

    @Serializable
    @SerialName("SnackbarError")
    data class Error(
        val text: String,
    ) : THSnackbarVisual
}

@Serializable
enum class SnackBarIcon {
    Done,
}

internal fun SnackbarVisuals.toFOData(): THSnackbarVisual {
    return Json.decodeFromString<THSnackbarVisual>(message)
}

internal fun SnackBarIcon.toIconSpec(): IconSpec = when (this) {
    SnackBarIcon.Done -> THDrawable.ic_check.toIconSpec()
}
