/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.contract

import com.example.utils.platform.viemodel.work.WorkScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

interface StateProvider<S> {
    fun fetchStateFlow(): StateFlow<S>
}

interface EventReceiver<E> {
    fun dispatchEvent(event: E)
}

interface EffectProvider<F> {
    suspend fun collectEffect(collector: FlowCollector<F>)
}


interface ContractProvider<S : BaseViewState, E : BaseEvent, A : BaseAction, F : BaseEffect> :
    StateProvider<S>, EventReceiver<E>, EffectProvider<F>