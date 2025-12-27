package com.example.module_injector.navigation

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavController


/**
 * Universal navigation extension
 * Supports:
 * - launchSingleTop (optional)
 * - popUpTo (optional)
 * - inclusive delete (Replace behavior)
 * - restore/save state (BottomNav behavior)
 * - custom nav options builder
 */

typealias OnNavigateTo = (Navigable, NavOptionsBuilder.() -> Unit) -> Unit

fun NavController.smartNavigateTo(
    route: Navigable,
    launchSingleTop: Boolean = true,
    popUpToRoute: Navigable? = null,
    inclusive: Boolean = false,
    restoreState: Boolean = true,
    saveState: Boolean = true,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.navigate(route) {
        this.launchSingleTop = launchSingleTop

        if (popUpToRoute != null) {
            popUpTo(popUpToRoute) {
                this.inclusive = inclusive
                this.saveState = saveState
            }
        }

        this.restoreState = restoreState

        // Additional customizations allowed
        builder()
    }
}