/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.common

import com.example.impl.domain.model.HomeFailures
import com.example.utils.functional.TimeShiftException
import com.example.utils.handlers.ErrorHandler
import javax.inject.Inject

internal class HomeErrorHandler @Inject constructor(): ErrorHandler<HomeFailures> {
    override suspend fun handle(error: Throwable): HomeFailures {
        return if (error is TimeShiftException) HomeFailures.ShiftError
        else if (error is TimeTaskImportanceException) HomeFailures.ImportanceError
        else HomeFailures.OtherError(error as Error)
    }
}