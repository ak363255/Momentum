/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.impl.presentation.theme.token

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.ui.theme.tokens.MomentumLanguage

internal interface HomeCoreStrings {
    val topAppbarHomeTitle: String
    val topAppbarCategoriesTitle: String
    val topAppbarOverviewTitle: String
    val datePickerTitle : String
    val datePickerHeadline : String

    val createScheduleTitle :String

    val emptyScheduleTitle :String
}

internal val LocalHomeStrings =
    staticCompositionLocalOf<HomeCoreStrings> { error("Please provide...") }

internal fun fetchHomeStrings(language: MomentumLanguage): HomeCoreStrings {
    return when (language) {
        MomentumLanguage.HINDI -> HiHomeStrings
        MomentumLanguage.ENGLISH -> EngHomeStrings
        MomentumLanguage.DEFAULT -> EngHomeStrings
    }
}


