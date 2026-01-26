package com.mrsanglier.tsumegohero.dashboard.navigation

import androidx.lifecycle.ViewModel
import com.mrsanglier.tsumegohero.coreui.componants.loading.LoadingManager
import com.mrsanglier.tsumegohero.coreui.componants.modalbottomsheet.THModalBottomSheetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel(
    private val loadingManager: LoadingManager,
) : ViewModel() {
    private val _bottomSheet = MutableStateFlow<THModalBottomSheetState?>(null)
    val bottomSheet: StateFlow<THModalBottomSheetState?> get() = _bottomSheet.asStateFlow()
}
