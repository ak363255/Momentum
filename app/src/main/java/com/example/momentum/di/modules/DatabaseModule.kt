package com.example.momentum.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.datasources.setting.SettingsDataBase
import com.example.data.datasources.setting.SettingsDataBaseCallback
import com.example.data.datasources.setting.ThemeLocalDataSource
import com.example.data.datasources.setting.ThemeSettingsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Amit Kumar on 21/12/25
 */

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideThemeSettingsDao(settingsDataBase: SettingsDataBase): ThemeSettingsDao {
        return settingsDataBase.getThemeSettingsDao()
    }

    @Singleton
    @Provides
    fun provideSettingsDatabase(@ApplicationContext context: Context,settingsDataBaseCallback: SettingsDataBaseCallback): SettingsDataBase {
        return  SettingsDataBase.create(context,settingsDataBaseCallback)
    }

    @Singleton
    @Provides
    fun provideThemeLocalDataSource(themeLocalDataSource: ThemeLocalDataSource.Base): ThemeLocalDataSource = themeLocalDataSource
}