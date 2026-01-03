/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.categories

data class MainCategory(
    val categoryId : Int,
    val default : DefaultCategoryType = DefaultCategoryType.EMPTY,
    val customName : String?
)