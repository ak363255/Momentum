/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.store

import com.example.utils.platform.communications.state.StateCommunicator
import com.example.utils.platform.viemodel.contract.Actor
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.contract.EffectProvider
import com.example.utils.platform.viemodel.contract.Reducer
import com.example.utils.platform.viemodel.contract.StateProvider
import com.example.utils.platform.viemodel.work.WorkScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface BaseStore<S, E, A, F> : StateProvider<S>, EffectProvider<F> {
    fun sendEvent(event: E)
    suspend fun postEffect(effect: F)
    suspend fun updateState(transformation: suspend (S) -> S)
    suspend fun handleAction(action: A)

    suspend fun fetchState(): S

    abstract class Abstract<S : BaseViewState, E : BaseEvent, A : BaseAction, F : BaseEffect>(
        private val scope: CoroutineScope,
        private val actor: Actor<S, E, A, F>,
        private val stateCommunicator: StateCommunicator<S>,
        private val reducer: Reducer<S, A>,
    ) : BaseStore<S, E, A, F> {

        private val mutex = Mutex()
        private val eventChannel: Channel<E> = Channel(capacity = Channel.UNLIMITED)
        private val workScope = WorkScope.Base<S, E, A, F>(store = this)

        init {
            eventChannel
                .receiveAsFlow()
                .map {
                    actor.apply {
                        workScope.handleEvent(it)
                    }
                }
                .launchIn(scope)
        }

        override fun sendEvent(event: E) {
            eventChannel.trySend(event)
        }

        override suspend fun handleAction(action: A) =
            updateState {
                reducer.reduce(action, fetchState())
            }


        override suspend fun updateState(transformation: suspend (S) -> S) = mutex.withLock {
            stateCommunicator.update(transformation(fetchState()))
        }

        override suspend fun fetchState(): S {
            return stateCommunicator.read()
        }

        override fun fetchStateFlow(): StateFlow<S> = stateCommunicator.fetchStateFlow()
    }
}