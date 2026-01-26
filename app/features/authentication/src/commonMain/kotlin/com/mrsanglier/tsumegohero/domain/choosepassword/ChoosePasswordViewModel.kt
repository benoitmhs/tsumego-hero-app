package com.mrsanglier.tsumegohero.domain.choosepassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.mrsanglier.tsumegohero.app.coreui.resources.choosePassword_textField_label
import com.mrsanglier.tsumegohero.app.coreui.resources.choosePassword_textField_title
import com.mrsanglier.tsumegohero.app.coreui.resources.common_validate
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStatus
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.textfield.PasswordTextUiField
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.navigation.THDestination
import com.mrsanglier.tsumegohero.coreui.resources.THString
import com.mrsanglier.tsumegohero.domain.choosepseudo.ChoosePseudoDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChoosePasswordViewModel(
//    private val passwordCriteriaUseCase: PasswordCriteriaUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ChoosePasswordDestination = savedStateHandle.toRoute()

    val passwordTextUiField: PasswordTextUiField =
        PasswordTextUiField(
            title = THString.choosePassword_textField_title.toTextSpec(),
            placeholder = THString.choosePassword_textField_label.toTextSpec(),
            contentDescription = THString.choosePassword_textField_label.toTextSpec(),
            onValueChanged = { password ->
//                val criterias = passwordCriteriaUseCase(password)
//                _uiState.value = uiState.value.copy(
//                    button = uiState.value.button.copy(
//                        status = if (criterias.all { it.isValid }) ButtonStatus.Enabled else ButtonStatus.Disabled,
//                    ),
//                    criteriaCells = criterias.map { it.toUiCell() },
//                )
            },
        )

    private val _uiState = MutableStateFlow(initState())
    val uiState: StateFlow<ChoosePasswordViewModelState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<THDestination?>(null)
    val navigation: StateFlow<THDestination?> get() = _navigation.asStateFlow()

    private fun savePassword() {
        _navigation.value = ChoosePseudoDestination(
            email = args.email,
            password = passwordTextUiField.getValue(),
        )
    }

    fun consumeNavigation() {
        _navigation.value = null
    }

    private fun initState(): ChoosePasswordViewModelState = ChoosePasswordViewModelState(
        button = THButtonState(
            text = THString.common_validate.toTextSpec(),
            onClick = ::savePassword,
            status = ButtonStatus.Disabled,
        ),
        criteriaCells = emptyList(),
//        criteriaCells = passwordCriteriaUseCase(passwordTextUiField.getValue())
//            .map { it.toUiCell() },
    )
}
