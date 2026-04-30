package com.example.impl.common

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

internal object HomePageRoutes {

   @Serializable
   internal object HomeMainPage : Navigable{
      override val route: String
         get() = this.javaClass.simpleName
   }
}