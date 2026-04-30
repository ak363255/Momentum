/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.SettingsFeatureStarterApi
import com.example.module_injector.navigation.NavigationManager
import javax.inject.Inject

internal class SettingFeatureStarterApiImpl @Inject constructor(
    private val navigationManager: NavigationManager
) : SettingsFeatureStarterApi {
    override suspend fun navigateToSettingsScreen() {
        navigationManager.navigate(SettingFeatureRoutes.SettingsMainPage)
    }
}