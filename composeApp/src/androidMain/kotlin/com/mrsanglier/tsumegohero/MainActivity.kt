package com.mrsanglier.tsumegohero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mrsanglier.tsumegohero.di.THKoinApplication
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            THKoinApplication(
                config = {
                    androidContext(this@MainActivity)
                }
            ) {
                App()
            }
        }
    }
}
