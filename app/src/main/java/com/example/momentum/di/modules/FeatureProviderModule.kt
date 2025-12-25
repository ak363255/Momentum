/**
 * @author Amit Kumar on 26/12/25
 */

package com.example.momentum.di.modules

import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.SettingFeatureEntryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface FeatureProviderModule {

    @Binds
    @Singleton
    fun bindsFeatureModule(settingFeatureEntry: SettingFeatureEntryImpl): SettingFeatureEntry
}