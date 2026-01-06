/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.ui.theme.materials.AppTypography
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType
import com.example.ui.theme.materials.toColorScheme
import com.example.ui.theme.tokens.LanguageUiType
import com.example.ui.theme.tokens.LocalMomentumColors
import com.example.ui.theme.tokens.LocalMomentumIcons
import com.example.ui.theme.tokens.LocalMomentumLanguage
import com.example.ui.theme.tokens.LocalMomentumString
import com.example.ui.theme.tokens.fetchCoreIcons
import com.example.ui.theme.tokens.fetchCoreStrings
import com.example.ui.theme.tokens.fetchLanguage
import com.example.ui.theme.tokens.fetchMomentumColorsType

@Composable
fun MomentumTheme(
     languageUiType: LanguageUiType,
     themeUiType: ThemeUiType,
     colorsUiType: ColorsUiType,
     content : @Composable ()-> Unit
){
    val isDark = isSystemInDarkTheme()
    val appLanguage = remember(languageUiType){ fetchLanguage(languageUiType)}
    val coreStrings = remember(appLanguage){fetchCoreStrings(appLanguage)}
    val appColors = remember(themeUiType,isDark,colorsUiType){fetchMomentumColorsType(themeUiType,colorsUiType, isSystemInDarTheme = isDark)}
    val appIcons = remember { fetchCoreIcons() }
    MaterialTheme(
        colorScheme = themeUiType.toColorScheme(colorsUiType, isDark = isDark),
        typography = AppTypography,
        content = {
            CompositionLocalProvider(
                LocalMomentumLanguage provides appLanguage,
                LocalMomentumColors provides appColors,
                LocalMomentumString provides coreStrings,
                LocalMomentumIcons provides appIcons,
                content = {
                    Surface  (  modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                        content()
                    }
                }


            )
        },

    )
}