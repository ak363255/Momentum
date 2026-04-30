/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.module_injector

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

interface FeatureRootRoutes : Navigable {
    @Serializable
    object HomeFeatureRootRoute : FeatureRootRoutes {
        override val route: String
            get() = this.javaClass.simpleName
    }
    @Serializable
    object SettingsFeatureRootRoute : FeatureRootRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
    @Serializable
    object EditorFeatureRootRoute : FeatureRootRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
    @Serializable
    object QrScanFeatureRootRoute : FeatureRootRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
}