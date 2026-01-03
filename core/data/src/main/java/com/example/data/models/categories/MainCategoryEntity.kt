/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.categories.DefaultCategoryType

@Entity(tableName = "mainCategory")
data class MainCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id : Int = 0,
    @ColumnInfo("categoryName")
    val categoryName : String,
    @ColumnInfo("defaultCategoryType")
    val defaultCategoryType : DefaultCategoryType
)