package com.mrsanglier.tsumegohero.core.error

sealed class THError(
    message: String?,
    cause: Throwable?,
    open val code: THErrorCode
) : Exception(message, cause)

sealed interface THErrorCode
