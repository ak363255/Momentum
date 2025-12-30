/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.task

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.models.categories.MainCategoryDetails
import com.example.data.models.categories.SubCategoryEntity

data class TimeTaskDetails(
     @Embedded
     val timeTask : TimeTaskEntity,

    @Relation(
        parentColumn = "mainCategoryId",
        entityColumn = "id"
    )
    val mainCategory : MainCategoryDetails,

    @Relation(
        parentColumn = "subCategoryId",
        entityColumn = "id"
    )
    val subCategory : SubCategoryEntity



)