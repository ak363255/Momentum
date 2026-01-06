/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.materials

enum class ThemeUiType {
    DEFAULT, LIGHT, DARK;
    fun isDark(isSystemInDarkTheme: Boolean = false): Boolean = when(this){
        ThemeUiType.DEFAULT -> isSystemInDarkTheme
        ThemeUiType.LIGHT -> false
        ThemeUiType.DARK -> true
    }
}

enum class ColorsUiType {
    RED, PINK;

    fun fetchLightColorScheme() = when (this) {
        ColorsUiType.RED -> RedLightColorScheme
        ColorsUiType.PINK -> PinkDarkColorScheme
    }

    fun fetchDarkColorScheme() = when (this) {
        ColorsUiType.RED -> RedDarkColorScheme
        ColorsUiType.PINK -> PinkDarkColorScheme
    }
}

fun ThemeUiType.toColorScheme(colorType: ColorsUiType,isDark : Boolean = false) = when (this) {
    ThemeUiType.DEFAULT -> if(isDark) colorType.fetchDarkColorScheme() else colorType.fetchLightColorScheme()
    ThemeUiType.LIGHT -> colorType.fetchLightColorScheme()
    ThemeUiType.DARK -> colorType.fetchDarkColorScheme()
}
