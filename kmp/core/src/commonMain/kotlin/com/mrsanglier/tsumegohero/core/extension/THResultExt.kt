package com.mrsanglier.tsumegohero.core.extension

import com.mrsanglier.tsumegohero.core.error.THError
import com.mrsanglier.tsumegohero.core.result.THResult

inline fun <T> THResult<T>.handleResult(
    onSuccess: (data: T) -> Unit,
    onFailure: (THError?) -> Unit,
) {
    when (this) {
        is THResult.Failure<T> -> onFailure(error)
        is THResult.Success<T> -> onSuccess(successData)
    }
}