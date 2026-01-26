import ext.libs

plugins {
    id("io.gitlab.arturbosch.detekt")
}

//dependencies {
//    detektPlugins(libs.detekt.formatting)
//}

detekt {
    parallel = true
    buildUponDefaultConfig = true
    config.setFrom(file("/${rootProject.projectDir}/detekt-config.yml"))
    autoCorrect = true
    ignoreFailures = true

    source.setFrom(
        file("src/commonMain/kotlin"),
        file("src/androidMain/kotlin"),
        file("src/iosMain/kotlin") // Add more sources if needed
    )
}
