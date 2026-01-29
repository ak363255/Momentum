package com.example.impl.di

import androidx.room.Insert
import com.example.api.navigation.EditorFeatureNavigator
import com.example.impl.EditorFeatureNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigatorModule {
    @Provides
    @Singleton
    fun providesEditorFeatureNavigator(): EditorFeatureNavigator{
        return EditorFeatureNavigatorImpl()
    }
}