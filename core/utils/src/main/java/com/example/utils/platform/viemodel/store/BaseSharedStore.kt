/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.store

import com.example.utils.platform.communications.state.EffectCommunicator
import com.example.utils.platform.communications.state.StateCommunicator
import com.example.utils.platform.viemodel.contract.Actor
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.contract.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector

abstract class BaseSharedStore<S : BaseViewState, E : BaseEvent, A : BaseAction, F : BaseEffect>(
    scope: CoroutineScope,
    actor: Actor<S, E, A, F>,
    private val effectCommunicator: EffectCommunicator<F>,
    stateCommunicator: StateCommunicator<S>,
    reducer: Reducer<S, A>,
) : BaseStore.Abstract<S, E, A, F>(
    scope = scope,
    actor = actor,
    stateCommunicator = stateCommunicator,
    reducer = reducer,
) {

    override suspend fun postEffect(effect: F) {
        effectCommunicator.update(effect)
    }

    override suspend fun collectEffect(collector: FlowCollector<F>) {
        effectCommunicator.collect(collector)
    }
}


fun <S : BaseViewState, E : BaseEvent, A : BaseAction, F : BaseEffect> getMviStore(
    scope: CoroutineScope,
    effectCommunicator: EffectCommunicator<F>,
    stateCommunicator: StateCommunicator<S>,
    reducer: Reducer<S, A>,
    actor: Actor<S, E, A, F>
) = MviStore(
    scope = scope,
    actor = actor,
    effectCommunicator = effectCommunicator,
    stateCommunicator = stateCommunicator,
    reducer = reducer,
)

class MviStore<S : BaseViewState, E : BaseEvent, A : BaseAction, F : BaseEffect>(
    scope: CoroutineScope,
    actor: Actor<S, E, A, F>,
    effectCommunicator: EffectCommunicator<F>,
    stateCommunicator: StateCommunicator<S>,
    reducer: Reducer<S, A>,
) : BaseSharedStore<S, E, A, F>(
    scope = scope,
    actor = actor,
    effectCommunicator = effectCommunicator,
    stateCommunicator = stateCommunicator,
    reducer = reducer,
)