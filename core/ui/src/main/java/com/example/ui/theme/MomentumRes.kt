/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme

import androidx.compose.runtime.Composable
import com.example.ui.theme.tokens.CoreStrings
import com.example.ui.theme.tokens.LocalMomentumColors
import com.example.ui.theme.tokens.LocalMomentumLanguage
import com.example.ui.theme.tokens.LocalMomentumString
import com.example.ui.theme.tokens.MomentumColors
import com.example.ui.theme.tokens.MomentumLanguage

object MomentumRes{
    @Composable
    fun fetchAppLanguage(): MomentumLanguage{
        return LocalMomentumLanguage.current
    }

    @Composable
    fun fetchAppCoreStrings(): CoreStrings{
        return LocalMomentumString.current
    }

    @Composable
    fun fetchAppColors(): MomentumColors{
        return LocalMomentumColors.current
    }
}