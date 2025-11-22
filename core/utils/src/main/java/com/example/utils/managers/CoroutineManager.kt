/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.utils.managers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface CoroutineManager {
    fun runOnUi(scope: CoroutineScope,block:CoroutineBlock)
    fun runOnBackground(scope : CoroutineScope,block : CoroutineBlock)

    abstract class Abstract(
        val backgroundDispatcher : CoroutineDispatcher,
        val uiDispatcher : CoroutineDispatcher
    ) : CoroutineManager{
        override fun runOnBackground(scope: CoroutineScope, block: CoroutineBlock) {
            scope.launch(backgroundDispatcher, block = block)
        }

        override fun runOnUi(scope: CoroutineScope, block: CoroutineBlock) {
            scope.launch(uiDispatcher,block = block)
        }
    }
    class Base : Abstract(backgroundDispatcher = Dispatchers.IO, uiDispatcher = Dispatchers.Main)
}

typealias CoroutineBlock = suspend CoroutineScope.()-> Unit