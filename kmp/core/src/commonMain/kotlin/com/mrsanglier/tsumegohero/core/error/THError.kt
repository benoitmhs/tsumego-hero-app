package com.mrsanglier.tsumegohero.core.error

sealed class FOError(
    message: String?,
    cause: Throwable?,
    open val code: FOErrorCode
) : Exception(message, cause)

sealed interface FOErrorCode
