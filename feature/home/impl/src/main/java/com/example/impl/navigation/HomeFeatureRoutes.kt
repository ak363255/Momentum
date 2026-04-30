/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

internal interface HomeFeatureRoutes : Navigable{
    @Serializable
    object HomeRoute : HomeFeatureRoutes{
        override val route: String = this.javaClass.simpleName
    }
}