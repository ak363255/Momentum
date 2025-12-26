package com.example.momentum.di.modules

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
    fun bindsGlobalNavigationModule(globalNavigationProvider: GlobalNavigationProvider.Base): GlobalNavigationProvider
}