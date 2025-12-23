/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.data.coroutine

import com.example.domain.coroutine.DispatchersHolder
import com.example.utils.di.annotations.DefaultDispatcher
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.di.annotations.UiDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DispatcherHolderImpl @Inject constructor(
    @DefaultDispatcher override val defaultDispatcher: CoroutineDispatcher,
    @IoDispatcher override val ioDispatcher: CoroutineDispatcher,
    @UiDispatcher override val uiDispatcher: CoroutineDispatcher
) : DispatchersHolder