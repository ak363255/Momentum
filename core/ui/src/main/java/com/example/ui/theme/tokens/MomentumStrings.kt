/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.tokens

import androidx.compose.runtime.staticCompositionLocalOf

fun fetchCoreStrings(type: MomentumLanguage) = when(type){
    MomentumLanguage.HINDI -> HindiLanguage
    MomentumLanguage.ENGLISH -> EnglishLanguage
    MomentumLanguage.DEFAULT -> EnglishLanguage
}

val LocalMomentumString = staticCompositionLocalOf<CoreStrings>{ error("Please Provide strings") }