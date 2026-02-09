package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.core.extension.handleResult
import com.mrsanglier.tsumegohero.coreui.componants.loading.LoadingManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.SnackbarManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.showDone
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.showError
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.dashboardgame.usecase.GetTsumegoItemUseCase
import com.mrsanglier.tsumegohero.dashboardgame.usecase.ImportTsumegoUseCase
import com.mrsanglier.tsumegohero.dashboardgame.usecase.PrepopulateTsumegoDBUseCase
import io.github.vinceglb.filekit.PlatformFile
import io.github.vinceglb.filekit.nameWithoutExtension
import io.github.vinceglb.filekit.readString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlayViewModel(
    getTsumegoItemUseCase: GetTsumegoItemUseCase,
    prepopulateTsumegoDBUseCase: PrepopulateTsumegoDBUseCase,
    private val importTsumegoUseCase: ImportTsumegoUseCase,
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayViewModelState())
    val uiState: StateFlow<PlayViewModelState> get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            prepopulateTsumegoDBUseCase()
            getTsumegoItemUseCase().collect { items ->
                _uiState.value = uiState.value.copy(tsugemoItems = items)
            }
        }
    }

    fun saveTsumegoFiles(files: List<PlatformFile>) {
        viewModelScope.launch {
            loadingManager.withLoading {
                val filesMap = files.associate {
                    it.nameWithoutExtension to it.readString()
                }

                importTsumegoUseCase(filesMap).handleResult(
                    onSuccess = {
                        snackbarManager.showDone("file imported".toTextSpec()) // TODO: loco
                    },
                    onFailure = snackbarManager::showError,
                )
            }
        }
    }
}
