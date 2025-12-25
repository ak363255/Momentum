/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.work

import com.example.utils.functional.Either
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import kotlinx.coroutines.flow.Flow

interface FlowWorkProcessor<C: WorkCommand,A: BaseAction,F: BaseEffect> {

    suspend fun doWork(command : C): Flow<Either<A,F>>
}