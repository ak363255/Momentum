package com.example.momentum.di.modules

import androidx.navigation.NavGraphBuilder
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.SettingFeatureEntryImpl
import javax.inject.Inject

interface GlobalNavigationProvider {
    fun provideSettingFeature(): SettingFeatureEntry

    class Base @Inject constructor(): GlobalNavigationProvider{
        override fun provideSettingFeature(): SettingFeatureEntry {
            return SettingFeatureEntryImpl()
        }
    }
}