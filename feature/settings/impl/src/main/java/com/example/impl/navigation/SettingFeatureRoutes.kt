/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

internal interface SettingFeatureRoutes  : Navigable{
    @Serializable
    object SettingsMainPage: SettingFeatureRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
    @Serializable
    object SettingThemePage: SettingFeatureRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
}