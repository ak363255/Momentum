/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.work

import com.example.utils.functional.Either
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect

interface WorkProcessor<C: WorkCommand,A: BaseAction,F: BaseEffect> {

    suspend fun doWork(command : C): Either<A,F>
}

interface WorkCommand : BackgroundKey