/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.data.models.setting

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.settings.ColorsType
import com.example.domain.models.settings.LanguageType
import com.example.domain.models.settings.ThemeType

@Entity(tableName = "ThemeSettings")
data class ThemeSettingsEntity(
     @PrimaryKey(autoGenerate = false)
     val id:Int = 0,
     @ColumnInfo(name = "colors_type")
     val colorType : ColorsType = ColorsType.RED,
     @ColumnInfo(name = "language")
     val languageType: LanguageType = LanguageType.ENG,
     @ColumnInfo(name = "theme_colors")
     val themeType : ThemeType = ThemeType.DEFAULT,
)