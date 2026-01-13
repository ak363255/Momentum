/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WishlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWishList(wishlistEntity: WishlistEntity)

    @Query("SELECT * FROM wishlist_entity")
    fun getWishList(): Flow<List<WishlistEntity>>

    @Query("SELECT * FROM wishlist_entity WHERE id = :id")
    fun getWishlistById(id : Int): WishlistEntity
}