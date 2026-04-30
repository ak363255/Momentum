package com.example.momentum.di.modules

import com.example.module_injector.navigation.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @Singleton
    fun bindsNavigationManager(navigationManager: NavigationManager.Base): NavigationManager

}