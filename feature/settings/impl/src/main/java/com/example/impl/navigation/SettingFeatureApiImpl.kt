/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.SettingsFeatureApi
import com.example.api.navigation.SettingsFeatureStarterApi
import javax.inject.Inject

internal class SettingFeatureApiImpl @Inject constructor(private val settingsFeatureStarterApi: SettingsFeatureStarterApi) :
    SettingsFeatureApi {
    override fun getSettingFeatureStarterApi(): SettingsFeatureStarterApi =
        settingsFeatureStarterApi
}