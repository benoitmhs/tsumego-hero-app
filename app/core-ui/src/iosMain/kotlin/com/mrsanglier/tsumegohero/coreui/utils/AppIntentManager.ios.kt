package com.mrsanglier.tsumegohero.coreui.utils

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

class AppIntentManagerImpl : AppIntentManager {
    override fun shareText(text: String) {
        val activityItems = listOf(text)
        val activityViewController = UIActivityViewController(activityItems, null)

        val application = UIApplication.sharedApplication
        application.keyWindow?.rootViewController?.presentViewController(
            activityViewController,
            animated = true,
            completion = null
        )
    }
}
