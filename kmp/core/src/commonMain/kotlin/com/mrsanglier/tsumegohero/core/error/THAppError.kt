package com.mrsanglier.tsumegohero.core.error

class FOAppError(
    val title: String? = null,
    override val message: String? = null,
    override val cause: Throwable? = null,
    override val code: Code,
) : FOError(message, cause, code) {
    enum class Code : FOErrorCode {
        ObjectNotFound,
        InvalidEmailFormat,
        SilentError,
    }
}

fun FOAppError.Code.toError(
    title: String? = null,
    message: String? = null,
    cause: Throwable? = null,
): FOAppError = FOAppError(
    title = title,
    message = message,
    cause = cause,
    code = this,
)
