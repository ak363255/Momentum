package com.example.momentum.di.modules

import com.example.api.navigation.HomeFeatureEntry
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.HomeFeatureEntryImpl
import com.example.impl.SettingFeatureEntryImpl
import javax.inject.Inject

interface TabFeatureProvider {
    fun provideSettingFeature(): SettingFeatureEntry
    fun provideHomeFeatureEntry(): HomeFeatureEntry

    class Base @Inject constructor(): TabFeatureProvider{
        override fun provideSettingFeature(): SettingFeatureEntry {
            return SettingFeatureEntryImpl()
        }

        override fun provideHomeFeatureEntry(): HomeFeatureEntry {
            return HomeFeatureEntryImpl()
        }
    }
}