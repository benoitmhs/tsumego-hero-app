package com.mrsanglier.tsumegohero.coreui.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class AppIntentManagerImpl(
    private val context: Context
) : AppIntentManager {

    override fun shareText(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }
}