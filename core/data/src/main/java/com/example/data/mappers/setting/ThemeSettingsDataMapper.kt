/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.data.mappers.setting

import com.example.data.models.setting.ThemeSettingsEntity
import com.example.domain.models.settings.ThemeSettings


fun ThemeSettingsEntity.toDomain() = ThemeSettings(
    colorsType = this.colorType,
    languageType = this.languageType,
    themeType = this.themeType
)

fun ThemeSettings.toEntity() = ThemeSettingsEntity(
    colorType = this.colorsType,
    languageType = this.languageType,
    themeType = this.themeType
)