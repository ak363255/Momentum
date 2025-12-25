/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.communications

import com.example.utils.platform.viemodel.contract.BaseEffect
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first


interface Communicator<S> {
    suspend fun read(): S
    fun update(state: S)
    suspend fun collect(collector: FlowCollector<S>)

    abstract class AbstractStateFlow<S>(initialState: S) : Communicator<S> {
        private var flow: MutableStateFlow<S> = MutableStateFlow(initialState)
        override suspend fun read(): S = flow.value

        override fun update(data: S) {
            flow.value = data
        }

        override suspend fun collect(collector: FlowCollector<S>) {
            flow.collect(collector)
        }
        fun fetchFlow()  : StateFlow<S> = flow
    }


    abstract class AbstractSharedFlow<F : BaseEffect>(
        replayCount: Int = 0,
        bufferCapacity: Int = 0,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
    ) : Communicator<F> {
        private var flow: MutableSharedFlow<F> =
            MutableSharedFlow(
                replay = replayCount, extraBufferCapacity = bufferCapacity,
                onBufferOverflow = onBufferOverflow
            )

        override suspend fun read(): F {
            return flow.first()
        }

        override fun update(data: F) {
            flow.tryEmit(data)
        }

        override suspend fun collect(collector: FlowCollector<F>) {
            flow.collect(collector)
        }

    }


}