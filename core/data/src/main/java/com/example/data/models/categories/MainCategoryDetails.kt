/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.categories

import androidx.room.Embedded
import androidx.room.Relation

data class MainCategoryDetails(
    @Embedded
    val mainCategory : MainCategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "mainCategoryId",
        entity = SubCategoryEntity::class
    )
    val subCategories : List<SubCategoryEntity>
)