plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://plugins.gradle.org/m2/")
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
}
