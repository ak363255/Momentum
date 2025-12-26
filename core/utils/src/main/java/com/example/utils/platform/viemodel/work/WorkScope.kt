/**
 * @author Amit Kumar on 30/11/25
 */

package com.example.utils.platform.viemodel.work

import android.util.Log
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineBlock
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.store.BaseStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface WorkScope<S,A,F> : WorkResultHandler<A,F>{
    suspend fun sendAction(action : A)
    suspend fun sendEffect(effect : F)

    suspend fun state():S

    fun launchBackgroundWork(
        scope : CoroutineScope,
        dispatcher : CoroutineDispatcher,
        block : CoroutineBlock,
        ): Job

    class Base<S: BaseViewState,E: BaseEvent,A: BaseAction,F: BaseEffect>(
        private val store: BaseStore<S,E,A,F>,

    ): WorkScope<S,A,F>{
        override suspend fun sendAction(action: A) {
            store.handleAction(action)
        }

        override suspend fun sendEffect(effect: F) {
            store.postEffect(effect)
        }

        override suspend fun state(): S {
            return store.fetchState()
        }

        override fun launchBackgroundWork(
            scope: CoroutineScope,
            dispatcher: CoroutineDispatcher,
            block: CoroutineBlock,

            ): Job {
            return scope.launch {
                withContext(dispatcher){
                    block()
                }
            }
        }

        override suspend fun Either<A, F>.handleWork() {
            Log.d("WORKSCOPE","handle called -> ${this}")
            when(this){
                is Either.Left<A> -> sendAction(this.data)
                is Either.Right<F> -> sendEffect(this.data)
            }
        }

        override suspend fun Flow<Either<A, F>>.collectAndHandleWork() {
            this.collect { it.handleWork() }
        }

    }
}

interface BackgroundKey