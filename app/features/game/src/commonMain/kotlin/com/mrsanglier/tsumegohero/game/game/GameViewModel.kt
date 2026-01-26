package com.mrsanglier.tsumegohero.game.game

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(

) : ViewModel() {

    private val _uiState = MutableStateFlow(GameViewModelState())
    val uiState: StateFlow<GameViewModelState> get() = _uiState.asStateFlow()
}
