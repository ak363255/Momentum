/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.impl.mappers

import com.example.domain.models.settings.ColorsType
import com.example.domain.models.settings.LanguageType
import com.example.domain.models.settings.ThemeType
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType
import com.example.ui.theme.tokens.LanguageUiType

fun LanguageType.mapToUi() = LanguageUiType.values().find { it.code==this.code }?: LanguageUiType.DEFAULT
fun ThemeType.mapToUi() = when(this){
    ThemeType.DEFAULT -> ThemeUiType.DEFAULT
    ThemeType.DARK -> ThemeUiType.DARK
    ThemeType.LIGHT -> ThemeUiType.LIGHT
}
fun ColorsType.mapToUi() = when(this){
    ColorsType.RED -> ColorsUiType.RED
    ColorsType.PINK -> ColorsUiType.PINK
}