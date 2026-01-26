package com.mrsanglier.tsumegohero

import androidx.compose.ui.window.ComposeUIViewController
import com.mrsanglier.tsumegohero.di.THKoinApplication
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(): UIViewController = ComposeUIViewController {
    THKoinApplication {
        App()
    }
}