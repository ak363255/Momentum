/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.data.datasources.setting

import com.example.data.mappers.setting.toDomain
import com.example.data.mappers.setting.toEntity
import com.example.domain.models.settings.ThemeSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface ThemeLocalDataSource{
    fun fetchThemeSettingsFlow(): Flow<ThemeSettings>
    suspend fun fetchThemeSettings(): ThemeSettings
    suspend fun updateThemeSettings(themeSettings: ThemeSettings)

    class Base @Inject constructor(private val  themeSettingsDao: ThemeSettingsDao): ThemeLocalDataSource{
        override fun fetchThemeSettingsFlow(): Flow<ThemeSettings> {
            return themeSettingsDao.fetchThemeSettingFlow().map { it.toDomain() }
        }

        override suspend fun fetchThemeSettings(): ThemeSettings {
            return themeSettingsDao.fetchThemeSetting().toDomain()
        }

        override suspend fun updateThemeSettings(themeSettings: ThemeSettings) {
            themeSettingsDao.updateThemeSetting(themeSettings.toEntity())
        }

    }

}