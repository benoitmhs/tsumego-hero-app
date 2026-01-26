package com.mrsanglier.tsumegohero.dashboard.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.app.coreui.resources.profile_logoutButton_label
import com.mrsanglier.tsumegohero.app.coreui.resources.snackbar_disconnected
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStatus
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.SnackbarManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.showDone
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
//    private val logoutUseCase: LogoutUseCase,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _logoutButtonState = MutableStateFlow(initialLogoutButton())
    val logoutButtonState: StateFlow<THButtonState> get() = _logoutButtonState.asStateFlow()

    private fun logout() {
        viewModelScope.launch(Dispatchers.Default) {
            _logoutButtonState.value = logoutButtonState.value.copy(
                status = ButtonStatus.Loading,
            )
//            logoutUseCase()
            snackbarManager.showDone(THString.snackbar_disconnected.toTextSpec())
            _logoutButtonState.value = logoutButtonState.value.copy(
                status = ButtonStatus.Enabled,
            )
        }
    }

    private fun initialLogoutButton(): THButtonState = THButtonState(
        text = THString.profile_logoutButton_label.toTextSpec(),
        status = ButtonStatus.Enabled,
        onClick = ::logout,
    )
}
