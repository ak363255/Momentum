/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.model.category

import com.example.domain.models.categories.DefaultCategoryType

data class MainCategoryUi(
    val id : Int = 0,
    val categoryName : String? = null,
    val defaultCategory : DefaultCategoryType = DefaultCategoryType.EMPTY
)