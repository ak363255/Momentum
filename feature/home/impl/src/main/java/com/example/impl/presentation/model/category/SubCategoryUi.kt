/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.model.category

internal data class SubCategoryUi(
    val id : Int,
    val mainCategory : MainCategoryUi = MainCategoryUi(),
    val subCategoryName : String?,
    val description : String?
)