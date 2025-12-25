/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.utils.platform.viemodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.communications.state.EffectCommunicator
import com.example.utils.platform.communications.state.StateCommunicator
import com.example.utils.platform.viemodel.contract.Actor
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.contract.ContractProvider
import com.example.utils.platform.viemodel.contract.Reducer
import com.example.utils.platform.viemodel.store.getMviStore
import com.example.utils.platform.viemodel.work.WorkScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<E : BaseEvent, A : BaseAction, F : BaseEffect, S : BaseViewState>(
    private val dispatcher: CoroutineDispatcher,
    private val effectCommunicator: EffectCommunicator<F>,
    private val stateCommunicator: StateCommunicator<S>
) : ViewModel(), ContractProvider<S,E,A,F>, Reducer<S,A>, Actor<S,E,A,F> {
    val TAG = "BASE-VIEWMODEL"
     val scope = viewModelScope
    val store = getMviStore(
        scope = scope,
        effectCommunicator = effectCommunicator,
        stateCommunicator = stateCommunicator,
        reducer = this,
        actor = this
    )

    override suspend fun collectEffect(collector: FlowCollector<F>) {
        store.collectEffect(collector)
    }

    override  fun fetchStateFlow(): StateFlow<S> {
        return stateCommunicator.fetchStateFlow()
    }

    override fun dispatchEvent(event: E) {
        store.sendEvent(event)
    }

}