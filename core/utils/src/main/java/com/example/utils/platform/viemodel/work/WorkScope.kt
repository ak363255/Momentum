/**
 * @author Amit Kumar on 30/11/25
 */

package com.example.utils.platform.viemodel.work

import com.example.utils.functional.Either
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseViewState
import kotlinx.coroutines.flow.Flow

interface WorkScope<S: BaseViewState,A: BaseAction,F: BaseEffect>{
    suspend fun sendAction(action : A)
    suspend fun sendEffect(effect : F)
    suspend fun Flow<Either<F, A>>.collectAndHandleFlow()
}