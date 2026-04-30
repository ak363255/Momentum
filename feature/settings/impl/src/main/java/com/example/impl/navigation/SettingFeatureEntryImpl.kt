package com.example.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.presentation.ui.settingsScreen
import javax.inject.Inject

/**
 * @author Amit Kumar on 26/12/25
 */
internal class SettingFeatureEntryImpl @Inject constructor(
) : SettingFeatureEntry() {
    override fun NavGraphBuilder.navigate() {
       settingsScreen()
    }
}