/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.utils.extensions.duration
import com.example.utils.functional.Constants.Date.HOURS_IN_DAY
import com.example.utils.functional.Constants.Date.MILLIS_IN_HOUR
import com.example.utils.functional.TimeRange
import com.example.utils.validation.ValidateError
import com.example.utils.validation.ValidateResult
import com.example.utils.validation.Validator
import javax.inject.Inject

internal interface TimeRangeValidator : Validator<TimeRange, TimeRangeError> {
    class Base @Inject constructor() : TimeRangeValidator {
        override fun validate(data: TimeRange): ValidateResult<TimeRangeError> {
            return if (duration(data) == 0L || duration(data) > MILLIS_IN_HOUR * HOURS_IN_DAY) {
                ValidateResult(false, TimeRangeError.DurationError)
            } else {
                ValidateResult(true, null)
            }
        }

    }
}

sealed class TimeRangeError : ValidateError {
    object DurationError : TimeRangeError()
}