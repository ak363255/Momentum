/**
 * @author Amit Kumar on 01/02/26
 */

package com.example.impl.routes

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

class SettingPageRoutes {
    @Serializable
    object SettingsMainPage: Navigable
    @Serializable
    object SettingThemePage: Navigable
}