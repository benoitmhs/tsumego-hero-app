package com.mrsanglier.tsumegohero.domain.choosepseudo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mrsanglier.tsumegohero.app.coreui.resources.common_validate
import com.mrsanglier.tsumegohero.app.coreui.resources.firstName_textField_title
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStatus
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.loading.LoadingManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.SnackbarManager
import com.mrsanglier.tsumegohero.coreui.componants.textfield.NormalTextUiField
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChoosePseudoViewModel(
//    private val createAccountUseCase: CreateAccountUseCase,
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: ChoosePseudoDestination = savedStateHandle.toRoute()

    private val _uiState = MutableStateFlow(initialState())
    val uiState: StateFlow<ChoosePseudoViewModelState> get() = _uiState.asStateFlow()

    internal val pseudoTextField = NormalTextUiField(
        title = THString.firstName_textField_title.toTextSpec(),
        contentDescription = THString.firstName_textField_title.toTextSpec(),
        onValueChanged = { pseudo ->
            _uiState.value = uiState.value.copy(
                button = uiState.value.button.copy(
                    status = if (pseudo.isEmpty()) ButtonStatus.Disabled else ButtonStatus.Enabled,
                )
            )
        }
    )

    private fun createAccount() {
        viewModelScope.launch(NonCancellable) {
            loadingManager.withLoading {
//                createAccountUseCase(
//                    email = args.email,
//                    password = args.password,
//                    username = pseudoTextField.getValue(),
//                ).let {
//                    when (it) {
//                        is THResult.Failure -> {
//                            snackbarManager.showError(it.error)
//                        }
//
//                        is THResult.Success -> {
//                            snackbarManager.showDone(THString.choosePseudo_message_accountCreated.toTextSpec())
//                        }
//                    }
//                }
            }
        }
    }

    private fun initialState(): ChoosePseudoViewModelState = ChoosePseudoViewModelState(
        button = THButtonState(
            text = THString.common_validate.toTextSpec(),
            status = ButtonStatus.Disabled,
            onClick = ::createAccount,
        )
    )
}
