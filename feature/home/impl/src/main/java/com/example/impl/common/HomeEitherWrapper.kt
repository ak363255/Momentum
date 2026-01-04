/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.common

import com.example.impl.domain.model.HomeFailures
import com.example.utils.wrappers.FlowEitherWrapper
import javax.inject.Inject

internal interface HomeEitherWrapper :
    FlowEitherWrapper<HomeFailures> {

    class Base @Inject constructor(
        private val homeErrorHandler: HomeErrorHandler
    ) : HomeEitherWrapper, FlowEitherWrapper.Abstract<HomeFailures>(homeErrorHandler)
}