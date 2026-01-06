package com.example.momentum.di.modules

import android.content.Context
import com.example.data.datasources.schedule.ScheduleDao
import com.example.data.datasources.schedule.ScheduleDatabase
import com.example.data.datasources.schedule.ScheduleDatabaseCallback
import com.example.data.datasources.schedule.ScheduleLocalDataSource
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


    @Singleton
    @Provides
    fun provideScheduleDatabase(@ApplicationContext context : Context,scheduleDatabaseCallback: ScheduleDatabaseCallback): ScheduleDatabase = ScheduleDatabase.create(context,scheduleDatabaseCallback)


    @Singleton
    @Provides
    fun provideScheduleDao(scheduleDatabase : ScheduleDatabase): ScheduleDao = scheduleDatabase.getScheduleDao()

    @Singleton
    @Provides
    fun provideScheduleLocalDataSource(scheduleLocalDataSource: ScheduleLocalDataSource.Base): ScheduleLocalDataSource = scheduleLocalDataSource

}