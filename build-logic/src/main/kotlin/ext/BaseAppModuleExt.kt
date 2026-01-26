package ext

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import config.Versioning
import org.jetbrains.kotlin.konan.properties.Properties

internal fun BaseAppModuleExtension.configureBuildTypes() {
    buildTypes {
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
