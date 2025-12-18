/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.tokens

import androidx.compose.runtime.staticCompositionLocalOf

enum class MomentumLanguage(val code: String){
    HINDI("hindi"),ENGLISH("eng")
}

fun fetchLanguage(type : LanguageUiType): MomentumLanguage{
    return when(type){
        LanguageUiType.HINDI -> MomentumLanguage.HINDI
        LanguageUiType.ENGLISH -> MomentumLanguage.ENGLISH
    }
}

val LocalMomentumLanguage  = staticCompositionLocalOf<MomentumLanguage> {
    error("Please provide language")
}

