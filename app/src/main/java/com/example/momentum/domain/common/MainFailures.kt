/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.domain.common

import com.example.utils.functional.DomainFailures

sealed class MainFailures : DomainFailures {
    data class OtherError(val throwable : Throwable): MainFailures()
}