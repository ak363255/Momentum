/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.domain.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface DispatchersHolder {
    val defaultDispatcher : CoroutineDispatcher
    val ioDispatcher: CoroutineDispatcher
    val uiDispatcher : CoroutineDispatcher
}