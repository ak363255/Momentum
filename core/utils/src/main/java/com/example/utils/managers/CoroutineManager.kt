/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.utils.managers

import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.di.annotations.UiDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CoroutineManager {
    fun runOnUi(scope: CoroutineScope, block: CoroutineBlock)
    fun runOnBackground(scope: CoroutineScope, block: CoroutineBlock)

    abstract class Abstract(
        val backgroundDispatcher: CoroutineDispatcher,
        val uiDispatcher: CoroutineDispatcher
    ) : CoroutineManager {
        override fun runOnBackground(scope: CoroutineScope, block: CoroutineBlock) {
            scope.launch(backgroundDispatcher, block = block)
        }

        override fun runOnUi(scope: CoroutineScope, block: CoroutineBlock) {
            scope.launch(uiDispatcher, block = block)
        }
    }

    class Base @Inject constructor(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        @UiDispatcher uiDispatcher: CoroutineDispatcher
    ) : Abstract(backgroundDispatcher = ioDispatcher, uiDispatcher = uiDispatcher)
}

typealias CoroutineBlock = suspend CoroutineScope.() -> Unit