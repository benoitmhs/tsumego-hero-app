package com.mrsanglier.tsumegohero.core.error

class THGameError(
    val title: String? = null,
    override val message: String? = null,
    override val cause: Throwable? = null,
    override val code: Code,
) : THError(message, cause, code) {
    enum class Code : THErrorCode {
        SgfFormatNotSupported,
    }
}

fun THGameError.Code.toError(
    title: String? = null,
    message: String? = null,
    cause: Throwable? = null,
): THGameError = THGameError(
    title = title,
    message = message,
    cause = cause,
    code = this,
)
