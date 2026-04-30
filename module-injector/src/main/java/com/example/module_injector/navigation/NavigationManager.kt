/**
 * @author Amit Kumar on 20/04/26
 */

package com.example.module_injector.navigation

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

abstract class NavigationManager {
     abstract val navigator: SharedFlow<Pair<Navigable, NavOptions?>>
   abstract suspend fun navigate(navigable: Navigable, option: NavOptions? = null)
     class Base @Inject constructor() : NavigationManager() {
        private val _navigator: MutableSharedFlow<Pair<Navigable, NavOptions?>> =
            MutableSharedFlow(replay = 0)
        override val navigator: SharedFlow<Pair<Navigable, NavOptions?>> = _navigator
        override suspend fun navigate(
            navigable: Navigable,
            option: NavOptions?
        ) {
            _navigator.emit(navigable to option)
        }

    }
}