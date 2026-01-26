package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import kotlinx.serialization.json.Json

sealed interface THSnackbarState {
    val withDismissAction: Boolean
    val duration: SnackbarDuration

    @Composable
    fun getSnackbarVisual(): THSnackbarVisual

    @Composable
    fun toSnackbarVisuals(): SnackbarVisuals = object : SnackbarVisuals {
        override val actionLabel: String? = null
        override val duration: SnackbarDuration = this@THSnackbarState.duration
        override val message: String = Json.encodeToString<THSnackbarVisual>(getSnackbarVisual())
        override val withDismissAction: Boolean = this@THSnackbarState.withDismissAction
    }

    data class Short(
        val text: TextSpec,
        val icon: SnackBarIcon? = null,
    ) : THSnackbarState {
        override val duration: SnackbarDuration = SnackbarDuration.Short
        override val withDismissAction: Boolean = false

        @Composable
        override fun getSnackbarVisual(): THSnackbarVisual =
            THSnackbarVisual.Short(
                text = text.string,
                icon = icon,
            )
    }

    data class Error(
        val text: TextSpec,
    ) : THSnackbarState {
        override val duration: SnackbarDuration = SnackbarDuration.Short
        override val withDismissAction: Boolean = true

        @Composable
        override fun getSnackbarVisual(): THSnackbarVisual =
            THSnackbarVisual.Error(
                text = text.string,
            )
    }
}