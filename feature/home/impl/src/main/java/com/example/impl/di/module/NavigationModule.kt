package com.example.impl.di.module

import com.example.api.navigation.HomeFeatureApi
import com.example.api.navigation.HomeFeatureEntry
import com.example.impl.navigation.HomeFeatureApiImpl
import com.example.impl.navigation.HomeFeatureEntryImpl
import com.example.impl.navigation.HomeFeatureStarterApiImpl
import com.example.module_injector.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun providesHomeFeatureEntry(): HomeFeatureEntry {
        return HomeFeatureEntryImpl()
    }

    @Provides
    fun providesHomeFeatureApi(navigationManager: NavigationManager): HomeFeatureApi {
        return HomeFeatureApiImpl(HomeFeatureStarterApiImpl(navigationManager))
    }
}