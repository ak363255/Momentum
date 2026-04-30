/**
 * @author Amit Kumar on 20/04/26
 */

package com.example.momentum

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.module_injector.navigation.NavigationManager
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class Navigator(
    private val lifecycleOwner: LifecycleOwner,
    private val navController: NavController,
    navigationManager: NavigationManager
) {
    init {
         collectWhenVisible(navigationManager.navigator){
             navController.navigate(it.first,it.second)
         }
    }

    // Collect SharedFlow only when activity is RESUMED (visible)
    private fun <T> collectWhenVisible(
        sharedFlow: SharedFlow<T>,
        onCollect: suspend (T) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                sharedFlow.collect { value ->
                    onCollect(value)
                }
            }
        }
    }
}