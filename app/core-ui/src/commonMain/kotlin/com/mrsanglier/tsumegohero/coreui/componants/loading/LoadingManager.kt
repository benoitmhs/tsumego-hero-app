package com.mrsanglier.tsumegohero.coreui.componants.loading

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.yield

class LoadingManager(
    private val loadingDelegate: LoadingVisibilityDelayDelegate = LoadingVisibilityDelayDelegate(),
) {
    private val _loadingState: MutableStateFlow<GlobalLoadingState> = MutableStateFlow(GlobalLoadingState.None)
    val loadingState: StateFlow<GlobalLoadingState> = _loadingState

    fun startLoading() {
        _loadingState.value = GlobalLoadingState.Blocking
        loadingDelegate.delayShowLoading {
            _loadingState.value = GlobalLoadingState.Loading
        }
    }

    fun stopLoading() {
        loadingDelegate.delayHideLoading {
            _loadingState.value = GlobalLoadingState.None
        }
    }

    fun startBlocking() {
        if (!loadingState.value.isBlocking && !loadingDelegate.isActive()) {
            _loadingState.value = GlobalLoadingState.Blocking
        }
    }

    fun stopBlocking() {
        // Keep loading state if set
        if (!loadingState.value.isLoading && !loadingDelegate.isActive()) {
            _loadingState.value = GlobalLoadingState.None
        }
    }

    /**
     * Safely run [block] with global loading enable. Call [yield] immediately after starting loading to allow UI refresh asap.
     */
    suspend inline fun <T> withLoading(
        noinline doFinally: (() -> Unit)? = null,
        block: () -> T,
    ): T {
        startLoading()
        return try {
            yield()
            block()
        } finally {
            doFinally?.invoke()
            stopLoading()
        }
    }
}
