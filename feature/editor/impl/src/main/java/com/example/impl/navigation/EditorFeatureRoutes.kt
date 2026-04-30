/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

internal interface EditorFeatureRoutes : Navigable{
    @Serializable
     object EditorRoute : EditorFeatureRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
}