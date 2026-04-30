/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.HomeFeatureStarterApi
import com.example.module_injector.navigation.NavigationManager
import javax.inject.Inject

internal class HomeFeatureStarterApiImpl @Inject constructor(private val navigationManager: NavigationManager) : HomeFeatureStarterApi {
    override suspend fun navigateToHomeScreen() {
        navigationManager.navigate(HomeFeatureRoutes.HomeRoute)
    }
}