/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.tokens

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.ui.theme.materials.ColorsUiType
import com.example.ui.theme.materials.ThemeUiType

class MomentumColors(
    val colorsUiType: ColorsUiType,
    val isDark: Boolean
)

fun fetchMomentumColorsType(themeUiType: ThemeUiType, colorsUiType: ColorsUiType,isSystemInDarTheme: Boolean = false): MomentumColors =
    MomentumColors(
        isDark = themeUiType.isDark(isSystemInDarTheme),
        colorsUiType = colorsUiType
    )


val LocalMomentumColors = staticCompositionLocalOf<MomentumColors> { error("Please provide color") }