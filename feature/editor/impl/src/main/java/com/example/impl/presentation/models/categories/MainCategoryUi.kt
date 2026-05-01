/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.models.categories

import com.example.domain.models.categories.DefaultCategoryType
import com.example.ui.mappers.mapToString
import com.example.ui.theme.tokens.CoreStrings

internal data class MainCategoryUi(
    val id: Int = 0,
    val customName: String? = null,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY
) {
    fun fetchName(strings: CoreStrings) = when (customName != null && customName != "null") {
        true -> customName
        false -> defaultType?.mapToString(strings)
    }
}

