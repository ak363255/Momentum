package com.example.momentum.di.modules

import com.example.api.navigation.EditorFeatureEntry
import com.example.api.navigation.HomeFeatureEntry
import com.example.api.navigation.SettingFeatureEntry
import com.example.impl.EditorFeatureEntryImpl
import com.example.impl.HomeFeatureEntryImpl
import com.example.impl.SettingFeatureEntryImpl
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
    fun bindsEditorFeature(editorFeatureEntryImpl: EditorFeatureEntryImpl): EditorFeatureEntry

    @Binds
    @Singleton
    fun bindsSettingFeature(settingsSettingFeatureEntryImpl: SettingFeatureEntryImpl): SettingFeatureEntry

    @Binds
    @Singleton
    fun bindsHomeFeature(homeFeatureEntryImpl: HomeFeatureEntryImpl): HomeFeatureEntry


}