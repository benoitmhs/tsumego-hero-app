package com.mrsanglier.tsumegohero.domain.connection

import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_login_button
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_login_emailTextField_label
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_login_passwordTextField_label
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_register_button
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_register_emailTextField_label
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStatus
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.loading.LoadingManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.SnackbarManager
import com.mrsanglier.tsumegohero.coreui.componants.textfield.InputType
import com.mrsanglier.tsumegohero.coreui.componants.textfield.NormalTextUiField
import com.mrsanglier.tsumegohero.coreui.componants.textfielddouble.BasicNormalTextUiField
import com.mrsanglier.tsumegohero.coreui.componants.textfielddouble.BasicPasswordTextField
import com.mrsanglier.tsumegohero.coreui.componants.textfielddouble.DoubleTextUiField
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ConnectionViewModel(
//    private val loginUseCase: LoginUseCase,
//    private val isEmailAvailableUseCase: IsEmailAvailableUseCase,
    internal val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState())
    val uiState: StateFlow<ConnectionUiState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<ConnectionNavigation?>(null)
    val navigation: StateFlow<ConnectionNavigation?> get() = _navigation.asStateFlow()

    private var registerJob: Job? = null

    val registerTextField: NormalTextUiField = NormalTextUiField(
        contentDescription = THString.connection_register_emailTextField_label.toTextSpec(),
        label = THString.connection_register_emailTextField_label.toTextSpec(),
        maxLine = 1,
        inputType = InputType.Email,
        onValueChanged = {
            _uiState.value = _uiState.value.copy(
                registerButtonState = _uiState.value.registerButtonState.copy(
                    status = if (it.isNotBlank()) ButtonStatus.Enabled else ButtonStatus.Disabled,
                ),
            )
        }
    )

    val doubleTextUiField = DoubleTextUiField(
        topTextUiField = BasicNormalTextUiField(
            label = THString.connection_login_emailTextField_label.toTextSpec(),
            contentDescription = THString.connection_login_emailTextField_label.toTextSpec(),
            imeAction = ImeAction.Next,
            initialValue = null,
            inputType = InputType.Email,
        ),
        bottomTextUiField = BasicPasswordTextField(
            label = THString.connection_login_passwordTextField_label.toTextSpec(),
            contentDescription = THString.connection_login_passwordTextField_label.toTextSpec(),
            initialValue = null,
        )
    ) { email, password ->
        _uiState.value = uiState.value.copy(
            loginButtonState = uiState.value.loginButtonState.copy(
                status = if (email.isNotEmpty() && password.isNotEmpty()) {
                    ButtonStatus.Enabled
                } else ButtonStatus.Disabled,
            )
        )
    }

    private fun initialUiState(): ConnectionUiState = ConnectionUiState(
        state = ConnectionUiState.State.Idle,
        loginButtonState = THButtonState(
            text = THString.connection_login_button.toTextSpec(),
            style = ButtonStyle.Primary,
            onClick = ::switchToLoginState,
        ),
        registerButtonState = THButtonState(
            text = THString.connection_register_button.toTextSpec(),
            style = ButtonStyle.Outlined,
            onClick = ::switchToRegisterState,
        )
    )

    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            loadingManager.withLoading {
//                val result = loginUseCase(
//                    email = doubleTextUiField.topTextUiField.getValue(),
//                    password = doubleTextUiField.bottomTextUiField.getValue(),
//                )
//                when (result) {
//                    is THResult.Success -> {
//                        snackbarManager.showDone(THString.connection_message_loginSuccess.toTextSpec())
//                    }
//
//                    is THResult.Failure -> {
//                        when (result.error?.code) {
//                            THRemoteError.Code.InvalidCredential -> doubleTextUiField.showError(result.error?.messageText())
//                            FOAppError.Code.InvalidEmailFormat -> doubleTextUiField.showError(result.error?.messageText())
//                            else -> snackbarManager.showError(result.error)
//                        }
//                    }
//                }
            }
        }
    }

    private fun register() {
        registerJob = viewModelScope.launch {
            updateRegisterButtonStatus(ButtonStatus.Loading)
            registerTextField.enabled = false

            val email = registerTextField.getValue()
//            val result = isEmailAvailableUseCase(email)
//            when (result) {
//                is THResult.Success -> {
//                    if (result.successData) {
//                        _navigation.value = ConnectionNavigation.ChoosePassword(email)
//                        updateRegisterButtonStatus(ButtonStatus.Enabled)
//                    } else {
//                        updateRegisterButtonStatus(buttonStatus = ButtonStatus.Enabled)
//                        registerTextField.displayError(THString.connection_error_emailTaken.toTextSpec())
//                    }
//                }
//
//                is THResult.Failure -> {
//                    when (result.error?.code) {
//                        FOAppError.Code.InvalidEmailFormat -> {
//                            registerTextField.displayError(result.error?.messageText())
//                        }
//
//                        else -> snackbarManager.showError(result.error)
//                    }
//                    updateRegisterButtonStatus(ButtonStatus.Enabled)
//                }
//            }
            registerTextField.enabled = true
        }
    }

    fun backPressed() {
        if (uiState.value.registerButtonState.status == ButtonStatus.Loading) {
            registerJob?.cancel()
        } else {
            _navigation.value = ConnectionNavigation.Back
        }
    }

    private fun updateRegisterButtonStatus(buttonStatus: ButtonStatus) {
        _uiState.value = uiState.value.copy(
            registerButtonState = uiState.value.registerButtonState.copy(
                status = buttonStatus,
            )
        )
    }

    private fun switchToLoginState() {
        _uiState.value = uiState.value.copy(
            state = ConnectionUiState.State.Login,
            loginButtonState = uiState.value.loginButtonState.copy(
                onClick = ::login,
                style = ButtonStyle.Primary,
                status = if (
                    doubleTextUiField.topTextUiField.getValue().isNotEmpty()
                    && doubleTextUiField.bottomTextUiField.getValue().isNotEmpty()
                ) {
                    ButtonStatus.Enabled
                } else ButtonStatus.Disabled,
            ),
            registerButtonState = uiState.value.registerButtonState.copy(
                style = ButtonStyle.Outlined,
                onClick = ::switchToRegisterState,
                status = ButtonStatus.Enabled,
            )
        )
    }

    fun consumeNavigation() {
        _navigation.value = null
    }

    private fun switchToRegisterState() {
        _uiState.value = uiState.value.copy(
            state = ConnectionUiState.State.Register,
            loginButtonState = uiState.value.loginButtonState.copy(
                style = ButtonStyle.Outlined,
                onClick = ::switchToLoginState,
                status = ButtonStatus.Enabled,
            ),
            registerButtonState = uiState.value.registerButtonState.copy(
                style = ButtonStyle.Primary,
                onClick = ::register,
                status = if (registerTextField.getValue().isNotBlank()) ButtonStatus.Enabled else ButtonStatus.Disabled,
            )
        )
    }
}

internal sealed interface ConnectionNavigation {
    data object Back : ConnectionNavigation
    data class ChoosePassword(val email: String) : ConnectionNavigation
}
