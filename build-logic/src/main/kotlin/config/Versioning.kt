package config

internal object Versioning {
    private const val LOCAL_VERSION_CODE = 1
    private const val LOCAL_VERSION_NAME = "0.1.0"

    internal fun versionCode(): Int = LOCAL_VERSION_CODE
    internal fun versionName(): String = LOCAL_VERSION_NAME
}
