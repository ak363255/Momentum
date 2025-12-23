/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.data.repository.setting

import com.example.data.datasources.setting.ThemeLocalDataSource
import com.example.data.mappers.setting.toEntity
import com.example.domain.models.settings.ThemeSettings
import com.example.domain.repository.settings.ThemeSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeSettingsRepositoryImpl @Inject constructor(
    private val localDataSource: ThemeLocalDataSource
) : ThemeSettingsRepository {
    override fun fetchThemeSettingsFlow(): Flow<ThemeSettings> {
        return localDataSource.fetchThemeSettingsFlow()
    }

    override suspend fun fetchThemeSettings(): ThemeSettings {
        return localDataSource.fetchThemeSettings()
    }

    override suspend fun updateThemeSettings(themeSettings: ThemeSettings) {
        localDataSource.updateThemeSettings(themeSettings)
    }
}