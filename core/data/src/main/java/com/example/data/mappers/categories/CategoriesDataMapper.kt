/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.data.mappers.categories

import com.example.data.models.categories.MainCategoryEntity
import com.example.data.models.categories.SubCategoryEntity
import com.example.domain.models.categories.DefaultCategoryType
import com.example.domain.models.categories.MainCategory
import com.example.domain.models.categories.SubCategory

fun MainCategoryEntity.mapToDomain() = MainCategory(
    categoryId = this.id,
    default = this.defaultCategoryType,
    customName = this.categoryName
)

fun MainCategory.mapToData() = MainCategoryEntity(
    id = this.categoryId,
    categoryName = this.customName?: DefaultCategoryType.EMPTY.name,
    defaultCategoryType = this.default
)


fun SubCategoryEntity.mapToDomain(mainCategory: MainCategory) = SubCategory(
    id = this.id,
    name = this.subCategoryName ,
    description = this.subCategoryDes,
    mainCategory = mainCategory
)

fun SubCategory.mapToData() = SubCategoryEntity(
    id = this.id,
    subCategoryName = this.name?: "",
    subCategoryDes = this.description,
    mainCategoryId = this.mainCategory.categoryId
)
