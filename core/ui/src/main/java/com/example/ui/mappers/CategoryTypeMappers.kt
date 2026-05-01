/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.ui.mappers

import com.example.domain.models.categories.DefaultCategoryType
import com.example.ui.theme.tokens.CoreStrings
import com.example.ui.theme.tokens.MomentumIcons

fun DefaultCategoryType.mapToIcon(icons: MomentumIcons): Int = when(this) {
    DefaultCategoryType.WORK -> icons.categoryWorkIcon
    DefaultCategoryType.REST -> icons.categoryRestIcon
    DefaultCategoryType.TRANSPORT -> icons.categoryTransportIcon
    DefaultCategoryType.STUDY -> icons.categoryStudyIcon
    DefaultCategoryType.SPORT -> icons.categorySportIcon
    DefaultCategoryType.SLEEP -> icons.categorySleepIcon
    DefaultCategoryType.CULTURE -> icons.categoryCultureIcon
    DefaultCategoryType.OTHER -> icons.categoryOtherIcon
    DefaultCategoryType.EMPTY -> icons.categoryEmptyIcon
    DefaultCategoryType.HYGIENE -> icons.categoryHygiene
    DefaultCategoryType.HEALTH -> icons.categoryHealth
    DefaultCategoryType.SHOPPING -> icons.categoryShopping
    DefaultCategoryType.CHORES -> icons.categoryAffairsIcon
    DefaultCategoryType.ENTERTAINMENT -> icons.categoryEntertainmentsIcon
    DefaultCategoryType.EATING -> icons.categoryEatIcon
    DefaultCategoryType.MISC -> icons.categoryOtherIcon
}

fun DefaultCategoryType.mapToString(strings: CoreStrings): String = when (this) {
    DefaultCategoryType.WORK -> strings.categoryWorkTitle
    DefaultCategoryType.REST -> strings.categoryRestTitle
    DefaultCategoryType.TRANSPORT -> strings.categoryTransportTitle
    DefaultCategoryType.STUDY -> strings.categoryStudyTitle
    DefaultCategoryType.SPORT -> strings.categorySportTitle
    DefaultCategoryType.SLEEP -> strings.categorySleepTitle
    DefaultCategoryType.CULTURE -> strings.categoryCultureTitle
    DefaultCategoryType.OTHER -> strings.categoryOtherTitle
    DefaultCategoryType.EMPTY -> strings.categoryEmptyTitle
    DefaultCategoryType.HYGIENE -> strings.categoryHygieneTitle
    DefaultCategoryType.HEALTH -> strings.categoryHealthTitle
    DefaultCategoryType.SHOPPING -> strings.categoryShoppingTitle
    DefaultCategoryType.CHORES -> strings.categoryChoresTitle
    DefaultCategoryType.ENTERTAINMENT -> strings.categoryEntertainmentsTitle
    DefaultCategoryType.EATING -> strings.categoryEatTitle
    DefaultCategoryType.MISC -> strings.categoryOtherTitle
}