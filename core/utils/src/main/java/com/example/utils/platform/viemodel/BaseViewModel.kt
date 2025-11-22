/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.utils.platform.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive

abstract class BaseViewModel<E: BaseEvent,A: BaseAction,F: BaseEffect,S: BaseViewState>(
    private val coroutineManager: CoroutineManager,
    private val initialState : S
) : ViewModel(){
    private val _state : MutableStateFlow<S> = MutableStateFlow(initialState)
    val state : StateFlow<S> get() = _state
    private val _effect : MutableSharedFlow<F> = MutableSharedFlow()
    val effect : SharedFlow<F> get() = _effect

    private val event : Channel<E> = Channel(capacity = Channel.UNLIMITED, onBufferOverflow = BufferOverflow.SUSPEND)

    private val scope : CoroutineScope get() = viewModelScope
    init {
        start()
    }

    private fun start(){
        coroutineManager.runOnBackground(scope){
            while(isActive){
                handleEvent(event.receive())
            }
        }
    }

    private fun sendEffect(effect:F){
        _effect.tryEmit(effect)
    }

    fun sendEvent(event:E){
        this.event.trySend(event)
    }

    suspend fun Flow<Either<F, A>>.collectAndHandle(){
        collect { either ->
            when(either){
                is Either.Left<F> -> sendEffect(either.data)
                is Either.Right<A> -> _state.update {
                    reduce(either.data,_state.value)
                }
            }
        }
    }


    abstract suspend fun handleEvent(event:E)
    abstract suspend fun reduce(action:A,state : S):S

}