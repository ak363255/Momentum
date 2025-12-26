/**
 * @author Amit Kumar on 26/12/25
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.presentation.ui.settingsScreen
import javax.inject.Inject


class SettingFeatureEntryImpl @Inject constructor() : SettingFeatureEntry() {
    override fun NavGraphBuilder.navigate(
        navHostController: NavHostController,
    ) {
       settingsScreen { destination,navOptions ->
           navHostController.navigate(destination,navOptions)
       }
    }
}
