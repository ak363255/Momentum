/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.domain.model

import com.example.utils.functional.DomainFailures

internal sealed class HomeFailures  : DomainFailures{
    data object ShiftError: HomeFailures()
    data object ImportanceError : HomeFailures()
    data class OtherError(val error: Error) : HomeFailures()
}