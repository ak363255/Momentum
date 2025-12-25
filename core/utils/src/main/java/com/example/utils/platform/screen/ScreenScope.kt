/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.contract.ContractProvider
import kotlinx.coroutines.flow.FlowCollector

interface ScreenScope<S,E,A,F> {
    fun dispatchEvent(event : E)
    @Composable
    fun fetchState():S
    @Composable fun collectEffect(collector : FlowCollector<F>)

    class Base<S: BaseViewState,E: BaseEvent,A: BaseAction,F: BaseEffect>(
        private val contractProvider: ContractProvider<S,E,A,F>
    ): ScreenScope<S,E,A,F>{
        override fun dispatchEvent(event: E) {
            contractProvider.dispatchEvent(event)
        }

        @Composable
        override fun fetchState(): S  = contractProvider.fetchStateFlow().collectAsStateWithLifecycle().value

        @Composable
        override fun collectEffect(collector: FlowCollector<F>) {
            LaunchedEffect(Unit) {
                contractProvider.collectEffect(collector)
            }
        }

    }
}