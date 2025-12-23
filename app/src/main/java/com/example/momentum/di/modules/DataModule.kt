/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.data.repository.setting.ThemeSettingsRepositoryImpl
import com.example.domain.repository.settings.ThemeSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindThemeSettingRepository(themeSettingsRepository: ThemeSettingsRepositoryImpl): ThemeSettingsRepository
}