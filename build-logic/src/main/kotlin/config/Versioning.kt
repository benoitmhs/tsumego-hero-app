package config

internal object Versioning {
    private const val LOCAL_VERSION_CODE = 2
    private const val LOCAL_VERSION_NAME = "0.0.2"

    internal fun versionCode(): Int = LOCAL_VERSION_CODE
    internal fun versionName(): String = LOCAL_VERSION_NAME
}
