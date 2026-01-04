/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.mappers.category

import com.example.domain.models.categories.MainCategory
import com.example.domain.models.categories.SubCategory
import com.example.impl.presentation.model.category.MainCategoryUi
import com.example.impl.presentation.model.category.SubCategoryUi

fun MainCategory.mapToUi() = MainCategoryUi(
    id = this.categoryId,
    categoryName = this.customName,
    defaultCategory = this.default
)

fun MainCategoryUi.mapToDomain() = MainCategory(
    categoryId = this.id,
    customName = this.categoryName,
    default = this.defaultCategory
)

fun SubCategory.mapToUi() = SubCategoryUi(
    id = this.id,
    subCategoryName = this.name,
    mainCategory = this.mainCategory.mapToUi(),
    description = this.description
)

fun SubCategoryUi.mapToDomain() = SubCategory(
    id = this.id,
    name = this.subCategoryName,
    mainCategory = this.mainCategory.mapToDomain(),
    description = this.description
)