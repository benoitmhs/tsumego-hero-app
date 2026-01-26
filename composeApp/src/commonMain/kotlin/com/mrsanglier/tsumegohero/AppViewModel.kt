package com.mrsanglier.tsumegohero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class AppViewModel() : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    internal val mainGraph: StateFlow<MainGraph> =
        // TODO: use authentication flow
        flowOf(MainGraph.Authenticated)
            .stateIn(viewModelScope, SharingStarted.Lazily, MainGraph.Init)
}

internal sealed interface MainGraph {
    data object ForceUpdate : MainGraph
    data object Maintenance : MainGraph
    data object Init : MainGraph
    data object Authenticated : MainGraph
    data object NoAuthenticated : MainGraph
}
