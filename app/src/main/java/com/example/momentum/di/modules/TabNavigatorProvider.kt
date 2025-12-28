package com.example.momentum.di.modules

import com.example.api.navigation.HomeFeatureEntry
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.HomeFeatureEntryImpl
import com.example.impl.SettingFeatureEntryImpl
import javax.inject.Inject

interface TabNavigatorProvider {
    fun provideSettingFeature(): SettingFeatureEntry
    fun provideHomeFeatureEntry(): HomeFeatureEntry

    class Base @Inject constructor(): TabNavigatorProvider{
        override fun provideSettingFeature(): SettingFeatureEntry {
            return SettingFeatureEntryImpl()
        }

        override fun provideHomeFeatureEntry(): HomeFeatureEntry {
            return HomeFeatureEntryImpl()
        }
    }
}