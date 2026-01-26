package com.mrsanglier.tsumegohero.core.result

import com.mrsanglier.tsumegohero.core.error.FOError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlin.experimental.ExperimentalTypeInference

/**
 * Generic result wrapper to wrap data fetched asynchronously with a status.
 *
 * @param T Type of the wrapped data. Might be [Unit] if the expected result does not contain any data.
 */
sealed class THFlowResult<out T> {

    /**
     * Loading implementation of [THFlowResult]
     *
     * @param T @inheritDoc
     * @property partialData The data already fetched or null if non applicable
     * @property progress The current progress
     */
    data class Loading<out T>(val partialData: T? = null, val progress: Float? = null) : THFlowResult<T>()

    /**
     * Success implementation of [THFlowResult]
     *
     * @param T @inheritDoc
     * @property successData The final non-null data
     */
    data class Success<out T>(val successData: T) : THFlowResult<T>()

    /**
     * Success implementation of [THFlowResult]
     *
     * @param T @inheritDoc
     * @property error The throwable that caused the failure or null if non applicable
     * @property failureData The final data or null if non applicable
     */
    data class Failure<out T>(val error: FOError? = null, val failureData: T? = null) : THFlowResult<T>()

    /**
     * Common getter for the data of any result states.
     */
    val data: T?
        get() {
            return when (this) {
                is Loading -> partialData
                is Success -> successData
                is Failure -> failureData
            }
        }

    /**
     * Cast [THFlowResult] to [THResult] for [Success] and [Failure] results.
     * Throw [IllegalStateException] when called with a [Loading] result.
     */
    @Throws(IllegalStateException::class)
    fun asResult(): THResult<T> {
        return when (this) {
            is Loading -> error("Loading cannot be cast to FOResult")
            is Success -> THResult.Success(successData)
            is Failure -> THResult.Failure(error, failureData)
        }
    }

    companion object Companion {
        /**
         * Applies transform functions to each result of the given flow.
         * By default, failures preserve the throwable if present and loadings preserve the progress.
         *
         * @see [Flow.transform]
         */
        @OptIn(ExperimentalTypeInference::class)
        inline fun <T, R> Flow<THFlowResult<T>>.transformResult(
            @BuilderInference crossinline transformError: suspend FlowCollector<THFlowResult<R>>.(value: Failure<T>) -> Unit =
                FlowCollector<THFlowResult<R>>::transformError,
            @BuilderInference crossinline transformLoading: suspend FlowCollector<THFlowResult<R>>.(value: Loading<T>) -> Unit =
                FlowCollector<THFlowResult<R>>::transformLoading,
            @BuilderInference crossinline transform: suspend FlowCollector<THFlowResult<R>>.(value: Success<T>) -> Unit =
                FlowCollector<THFlowResult<R>>::transformSuccess,
        ): Flow<THFlowResult<R>> = flow {
            collect { result ->
                return@collect when (result) {
                    is Failure -> transformError(result)
                    is Loading -> transformLoading(result)
                    is Success -> transform(result)
                }
            }
        }

        /**
         * Returns a flow containing the results of applying the given [transform] function to each result of the original flow.
         * By default, failures preserve the throwable if present and loadings preserve the progress.
         *
         * @see [Flow.map]
         */
        inline fun <T, R> Flow<THFlowResult<T>>.mapResult(
            crossinline mapError: suspend (error: FOError?) -> FOError? = ::mapError,
            crossinline mapLoading: suspend (progress: Float?) -> Float? = ::mapLoading,
            crossinline mapSuccess: suspend (value: T) -> R = ::mapSuccess,
        ): Flow<THFlowResult<R>> = transformResult(
            transformError = { value ->
                return@transformResult emit(Failure(mapError(value.error), value.failureData?.let { mapSuccess(it) }))
            },
            transformLoading = { value ->
                return@transformResult emit(Loading(value.partialData?.let { mapSuccess(it) }, mapLoading(value.progress)))
            },
            transform = { value ->
                return@transformResult emit(Success(mapSuccess(value.successData)))
            },
        )

        fun <T> Flow<THFlowResult<T>>.unit(): Flow<THFlowResult<Unit>> = mapResult { /* Unit */ }
    }
}

// FIXME workaround use function ref instead of lambda for default params
//  https://youtrack.jetbrains.com/issue/KT-60506/IllegalArgumentException-suspend-default-lambda-cannot-be-inlined-caused-by-the-combination-of-suspend-functions-inlining-and
//  https://youtrack.jetbrains.com/issue/KT-45505/IAE-suspend-default-lambda-X-cannot-be-inlined-use-a-function-reference-instead-with-crossinline-suspend-lambda-and-default

@Suppress("RedundantSuspendModifier", "UNCHECKED_CAST")
@PublishedApi
internal suspend fun <T, R> mapSuccess(data: T): R = data as R

@Suppress("RedundantSuspendModifier")
@PublishedApi
internal suspend fun mapError(error: FOError?): FOError? = error

@Suppress("RedundantSuspendModifier")
@PublishedApi
internal suspend fun mapLoading(progress: Float?): Float? = progress

@Suppress("UNCHECKED_CAST")
@PublishedApi
internal suspend fun <T, R> FlowCollector<THFlowResult<R>>.transformSuccess(value: THFlowResult.Success<T>) {
    emit(value as THFlowResult<R>)
}

@PublishedApi
internal suspend fun <T, R> FlowCollector<THFlowResult<R>>.transformError(value: THFlowResult.Failure<T>) {
    emit(THFlowResult.Failure(error = value.error))
}

@PublishedApi
internal suspend fun <T, R> FlowCollector<THFlowResult<R>>.transformLoading(value: THFlowResult.Loading<T>) {
    emit(THFlowResult.Loading(progress = value.progress))
}
