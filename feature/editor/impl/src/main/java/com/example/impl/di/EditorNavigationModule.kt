/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.di

import com.example.api.navigation.EditorFeatureApi
import com.example.api.navigation.EditorFeatureEntry
import com.example.impl.domain.interactors.EditorInteractor
import com.example.impl.navigation.EditorFeatureApiImpl
import com.example.impl.navigation.EditorFeatureEntryImpl
import com.example.impl.navigation.EditorFeatureStarterApiImpl
import com.example.module_injector.navigation.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EditorNavigationModule {
    @Provides
    fun providesEditorFeatureEntry(): EditorFeatureEntry {
        return EditorFeatureEntryImpl()
    }

    @Provides
    @Singleton
    fun providesEditorFeatureApi(navigationManager: NavigationManager): EditorFeatureApi {
        return EditorFeatureApiImpl(EditorFeatureStarterApiImpl(navigationManager))
    }
}