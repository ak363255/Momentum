/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.utils.wrappers

import com.example.utils.functional.DomainFailures
import com.example.utils.functional.Either
import com.example.utils.handlers.ErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.CancellationException

interface EitherWrapper<F : DomainFailures> {
    suspend fun <O> wrap(block: suspend () -> O): Either<F, O>

    abstract class Abstract<F : DomainFailures>(private val errorHandler: ErrorHandler<F>) :
        EitherWrapper<F> {
        override suspend fun <O> wrap(block: suspend () -> O): Either<F, O> {
            return try {
                Either.Right(block())
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Either.Left(errorHandler.handle(e))
            }
        }
    }

}

interface FlowEitherWrapper<F : DomainFailures> : EitherWrapper<F>{
    fun <O> wrapFlow(block: () -> Flow<O>): Flow<Either<F, O>>

    abstract class  Abstract<F : DomainFailures>(private val errorHandler: ErrorHandler<F>) :
        FlowEitherWrapper<F>, EitherWrapper.Abstract<F>(errorHandler) {
        override fun <O> wrapFlow(block: () -> Flow<O>): Flow<Either<F, O>> {
            return flow {
                block.invoke().catch { error ->
                    this@flow.emit(Either.Left(data = errorHandler.handle(error)))
                }.collect { data ->
                    emit(Either.Right(data = data))
                }
            }
        }
    }
}

