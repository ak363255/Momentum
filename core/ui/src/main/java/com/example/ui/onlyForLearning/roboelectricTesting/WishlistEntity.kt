/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlist_entity")
class WishlistEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("wishes") val wishes: String
)