package com.mrsanglier.tsumegohero.core.result

import co.touchlab.kermit.Logger
import com.mrsanglier.tsumegohero.core.error.FOError

/**
 * Generic result wrapper to wrap data with a status.
 *
 * @param T Type of the wrapped data. Might be [Unit] if the expected result does not contain any data.
 */
sealed class THResult<out T> {

    /**
     * Success implementation of [THResult]
     *
     * @param T @inheritDoc
     * @property successData The final non-null data
     */
    data class Success<out T>(val successData: T) : THResult<T>()

    /**
     * Success implementation of [THResult]
     *
     * @param T @inheritDoc
     * @property throwable The throwable that caused the failure or null if non applicable
     * @property failureData The final data or null if non applicable
     */
    data class Failure<out T>(val error: FOError? = null, val failureData: T? = null) : THResult<T>()

    /**
     * Common getter for the data of any result states.
     */
    val data: T?
        get() {
            return when (this) {
                is Success -> successData
                is Failure -> failureData
            }
        }

    /**
     * Cast [THResult] to [THFlowResult]
     */
    fun asFlowResult(): THFlowResult<T> {
        return when (this) {
            is Success -> THFlowResult.Success(successData)
            is Failure -> THFlowResult.Failure(error, failureData)
        }
    }

    companion object Companion {
        suspend fun <T> suspendWithFOResult(
            mapError: (FOError) -> FOError = { it },
            block: suspend () -> T,
        ): THResult<T> = suspendFOResult(
            mapError = mapError,
        ) {
            Success(block())
        }

        suspend fun <T> suspendFOResult(
            mapError: (FOError) -> FOError = { it },
            block: suspend () -> THResult<T>,
        ): THResult<T> =
            try {
                block()
            } catch (e: FOError) {
                val piError = mapError(e)
                Logger.e { e.toString() }
                Logger.e { piError.stackTraceToString() }
                Failure(piError)
            }

        inline fun <reified T> catchResult(
            mapError: (FOError) -> FOError = { it },
            block: () -> T,
        ): THResult<T> =
            try {
                Success(block())
            } catch (e: FOError) {
                val piError = mapError(e)
                Logger.e { piError.stackTraceToString() }
                Failure(piError)
            }

        fun <T> withFOResult(
            mapError: (FOError) -> FOError = { it },
            block: () -> T,
        ): THResult<T> =
            try {
                Success(block())
            } catch (e: FOError) {
                val piError = mapError(e)
                Logger.e { piError.stackTraceToString() }
                Failure(piError)
            }
    }
}
