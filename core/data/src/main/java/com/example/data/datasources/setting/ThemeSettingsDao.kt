/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.data.datasources.setting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.models.setting.ThemeSettingsEntity
import com.example.domain.models.settings.ThemeSettings
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeSettingsDao{
    @Update
    fun updateThemeSetting(themeSetting: ThemeSettingsEntity)

    @Query("SELECT * FROM ThemeSettings WHERE id = 0")
    fun fetchThemeSetting(): ThemeSettingsEntity

    @Query("SELECT * FROM ThemeSettings WHERE id = 0")
    fun fetchThemeSettingFlow(): Flow<ThemeSettingsEntity>
}