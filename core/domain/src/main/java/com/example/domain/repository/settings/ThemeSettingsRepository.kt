/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.domain.repository.settings

import com.example.domain.models.settings.ThemeSettings
import kotlinx.coroutines.flow.Flow

interface ThemeSettingsRepository {
    fun fetchThemeSettingsFlow(): Flow<ThemeSettings>
    suspend fun fetchThemeSettings(): ThemeSettings
    suspend fun updateThemeSettings(themeSettings: ThemeSettings)
}