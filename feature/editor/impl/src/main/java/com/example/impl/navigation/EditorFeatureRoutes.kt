/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable
internal interface EditorFeatureRoutes : Navigable{
    @Serializable
    data class EditorRoute(val key: Long?,val startTime: Long) : EditorFeatureRoutes{
        override val route: String
            get() = this.javaClass.simpleName
    }
}