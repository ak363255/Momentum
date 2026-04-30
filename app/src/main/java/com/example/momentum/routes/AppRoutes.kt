/**
 * @author Amit Kumar on 01/02/26
 */

package com.example.momentum.routes

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

interface AppRoutes : Navigable {
    @Serializable
    object AppRootRoutes : AppRoutes {
        override val route: String
            get() = this.javaClass.simpleName
    }

}