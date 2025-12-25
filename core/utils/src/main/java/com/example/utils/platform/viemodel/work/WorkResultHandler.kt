/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.work

import com.example.utils.functional.Either
import kotlinx.coroutines.flow.Flow

interface WorkResultHandler<A,F> {
    suspend fun Either<A, F>.handleWork()
    suspend fun Flow<Either<A, F>>.collectAndHandleWork()
}