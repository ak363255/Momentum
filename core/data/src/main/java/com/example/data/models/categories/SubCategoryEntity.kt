/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "subCategoryEntity",
    foreignKeys = [
        ForeignKey(
            entity = MainCategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("mainCategoryId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SubCategoryEntity(
     @PrimaryKey(autoGenerate = true)
     @ColumnInfo("id")
    val id : Int,
    @ColumnInfo("subCategoryName")
    val subCategoryName : String,
    @ColumnInfo("subCategoryDes")
    val subCategoryDes : String?,
    @ColumnInfo("mainCategoryId")
    val mainCategoryId : Int
)