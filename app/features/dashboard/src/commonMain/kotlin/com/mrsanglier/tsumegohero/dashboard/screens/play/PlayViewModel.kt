package com.mrsanglier.tsumegohero.dashboard.screens.play

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlayViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PlayViewModelState())
    val uiState: StateFlow<PlayViewModelState> get() = _uiState.asStateFlow()
}
