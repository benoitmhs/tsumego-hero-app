package com.mrsanglier.tsumegohero.coreui.componants.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

sealed interface InputType {

    fun getKeyboardOption(imeAction: ImeAction = ImeAction.Done): KeyboardOptions

    object Default : InputType {
        override fun getKeyboardOption(imeAction: ImeAction): KeyboardOptions = KeyboardOptions.Default.copy(
            autoCorrectEnabled = false,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Sentences,
        )
    }

    object Email : InputType {
        override fun getKeyboardOption(imeAction: ImeAction): KeyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Email,
            imeAction = imeAction,
        )
    }

    object Number : InputType {
        override fun getKeyboardOption(imeAction: ImeAction): KeyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Number,
            imeAction = imeAction,
        )
    }

    object Password : InputType {
        override fun getKeyboardOption(imeAction: ImeAction): KeyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            // Avoid animation glitch due to keyboard
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
        )
    }
}
