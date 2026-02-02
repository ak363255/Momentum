/**
 * @author Amit Kumar on 26/12/25
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.presentation.ui.settingsScreen
import com.example.impl.routes.SettingPageRoutes
import com.example.module_injector.navigation.Navigable
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject


class SettingFeatureEntryImpl @Inject constructor(
) : SettingFeatureEntry() {
    private lateinit var navHostController: NavHostController

    override val featureRoute: Navigable
        get() = FeatureRootRoute.SettingRootRoute
    override fun NavGraphBuilder.navigate(navHostController: NavHostController) {
        this@SettingFeatureEntryImpl.navHostController = navHostController
       settingsScreen()
    }


}
