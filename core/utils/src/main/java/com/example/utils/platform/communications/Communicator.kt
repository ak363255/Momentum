/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.communications

import android.util.Log
import com.example.utils.platform.viemodel.contract.BaseEffect
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow


interface Communicator<S> {
    suspend fun read(): S
    fun update(state: S)
    suspend fun collect(collector: FlowCollector<S>)

    abstract class AbstractStateFlow<S>(initialState: S) : Communicator<S> {
        private var flow: MutableStateFlow<S> = MutableStateFlow(initialState)
        override suspend fun read(): S = flow.value

        override fun update(state: S) {
            flow.value = state
        }

        override suspend fun collect(collector: FlowCollector<S>) {
            flow.collect(collector)
        }
        fun fetchFlow()  : StateFlow<S> = flow
    }


    abstract class AbstractSharedFlow<F : BaseEffect>(
        replayCount: Int = 0,
        bufferCapacity: Int = 0,
        onBufferOverflow: BufferOverflow = BufferOverflow.DROP_OLDEST
    ) : Communicator<F> {
        private val flow = Channel<F>(capacity = bufferCapacity,onBufferOverflow = onBufferOverflow)

        override suspend fun read(): F {
            return flow.consumeAsFlow().first()
        }

        override fun update(state: F) {
            Log.d("WORK"," update ${state}")
            flow.trySend(state)
        }

        override suspend fun collect(collector: FlowCollector<F>) {
            flow.receiveAsFlow().collect(collector)
        }

    }


}