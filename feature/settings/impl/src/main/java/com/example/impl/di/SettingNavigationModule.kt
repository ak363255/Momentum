package com.example.impl.di

import com.example.api.navigation.SettingFeatureEntry
import com.example.api.navigation.SettingsFeatureApi
import com.example.impl.navigation.SettingFeatureApiImpl
import com.example.impl.navigation.SettingFeatureEntryImpl
import com.example.impl.navigation.SettingFeatureStarterApiImpl
import com.example.module_injector.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingNavigationModule {
    @Provides
    @Singleton
    fun providesSettingFeatureEntry(): SettingFeatureEntry {
        return SettingFeatureEntryImpl()
    }

    @Provides
    fun providesSettingsFeatureApi(navigationManager: NavigationManager): SettingsFeatureApi{
        return SettingFeatureApiImpl(SettingFeatureStarterApiImpl(navigationManager))
    }
}