package com.mrsanglier.tsumegohero.coreui.componants.loading

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(enabled: Boolean, onBack: () -> Unit)