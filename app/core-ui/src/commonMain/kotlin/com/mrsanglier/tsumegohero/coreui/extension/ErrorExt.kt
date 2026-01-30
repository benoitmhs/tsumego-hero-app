package com.mrsanglier.tsumegohero.coreui.extension

import com.mrsanglier.tsumegohero.app.coreui.resources.common_error_noNetwork
import com.mrsanglier.tsumegohero.app.coreui.resources.common_error_unknown
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_error_emailFormatInvalid
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_error_emailTaken
import com.mrsanglier.tsumegohero.app.coreui.resources.connection_error_invalidCredential
import com.mrsanglier.tsumegohero.core.error.THAppError
import com.mrsanglier.tsumegohero.core.error.THError
import com.mrsanglier.tsumegohero.core.error.THGameError
import com.mrsanglier.tsumegohero.core.error.THRemoteError
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString

fun THError.messageText(): TextSpec? = when (this.code) {
    // Remote error
    THRemoteError.Code.ErrorNetwork -> THString.common_error_noNetwork
    THRemoteError.Code.EmailAlreadyTaken -> THString.connection_error_emailTaken
    THRemoteError.Code.InvalidCredential -> THString.connection_error_invalidCredential
    THRemoteError.Code.MissingData,
    THRemoteError.Code.HTTPError,
    THRemoteError.Code.TimeOut,
    THRemoteError.Code.WeakPassword,
    THRemoteError.Code.Unauthorized,
    THRemoteError.Code.Unknown,
    THRemoteError.Code.AuthenticationRequired, // TODO: error strings
    THRemoteError.Code.BadRequest, // TODO: error strings
    THRemoteError.Code.InvalidShareCode, // TODO: error strings
    THRemoteError.Code.NotFound, // TODO: error strings
    THRemoteError.Code.ServerError, // TODO: error strings
    THGameError.Code.InvalidMove, // TODO: error strings
    THGameError.Code.WrongPlayerTurn, // TODO: error strings
    THGameError.Code.SgfFormatNotSupported, // TODO: error strings
        -> THString.common_error_unknown

    // App error
    THAppError.Code.ObjectNotFound -> THString.common_error_unknown
    THAppError.Code.InvalidEmailFormat -> THString.connection_error_emailFormatInvalid
    THAppError.Code.SilentError -> null
}?.toTextSpec()
