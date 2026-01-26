package com.mrsanglier.tsumegohero.core.error

class THRemoteError(
    override val code: Code,
    val title: String? = null,
    override val message: String? = null,
    override val cause: Throwable? = null,
) : FOError(message, cause, code) {
    enum class Code : FOErrorCode {
        AuthenticationRequired,
        BadRequest,
        EmailAlreadyTaken,
        ErrorNetwork,
        InvalidCredential,
        InvalidShareCode,
        MissingData,
        NotFound,
        HTTPError,
        ServerError,
        TimeOut,
        WeakPassword,
        Unauthorized,
        Unknown,
    }

    companion object Companion {
        fun Code.toError(
            title: String? = null,
            message: String? = null,
            cause: Throwable? = null,
        ): THRemoteError = THRemoteError(
            code = this,
            title = title,
            message = message,
            cause = cause,
        )
    }
}