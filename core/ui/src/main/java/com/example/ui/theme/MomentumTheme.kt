/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType
import com.example.ui.theme.materials.toColorScheme
import com.example.ui.theme.tokens.LanguageUiType
import com.example.ui.theme.tokens.LocalMomentumColors
import com.example.ui.theme.tokens.LocalMomentumLanguage
import com.example.ui.theme.tokens.LocalMomentumString
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
    val appLanguage = fetchLanguage(languageUiType)
    val coreStrings = fetchCoreStrings(appLanguage)
    val appColors = fetchMomentumColorsType(themeUiType,colorsUiType)
    MaterialTheme(
        colorScheme = themeUiType.toColorScheme(colorsUiType),
        content = {
            CompositionLocalProvider(
                LocalMomentumLanguage provides appLanguage,
                LocalMomentumColors provides appColors,
                LocalMomentumString provides coreStrings,
                content = content
            )
        }

    )
}