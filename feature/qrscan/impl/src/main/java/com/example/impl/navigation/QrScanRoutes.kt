package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

/**
 * @author Amit Kumar on 25/04/26
 */
sealed interface QrScanRoutes : Navigable {
    @Serializable
    object QrScan : QrScanRoutes {
        override val route: String
            get() = this.javaClass.simpleName
    }
}