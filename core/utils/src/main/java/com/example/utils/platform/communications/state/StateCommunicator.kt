/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.communications.state

import com.example.utils.platform.communications.Communicator
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseViewState
import kotlinx.coroutines.flow.StateFlow

interface StateCommunicator<S: BaseViewState>: Communicator<S> {

    fun fetchStateFlow(): StateFlow<S>
     abstract class Abstract<S: BaseViewState>(val initialState : S): StateCommunicator<S>,
        Communicator.AbstractStateFlow<S>(initialState = initialState){
         override fun fetchStateFlow(): StateFlow<S> = fetchFlow()
        }
}

interface EffectCommunicator<F: BaseEffect> : Communicator<F>{
    abstract class Abstract<F: BaseEffect>: EffectCommunicator<F>, Communicator.AbstractSharedFlow<F>()
}