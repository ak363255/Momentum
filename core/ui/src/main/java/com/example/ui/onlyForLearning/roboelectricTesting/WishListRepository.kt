/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface WishListRepository {
    fun saveWishlist(wishlist: Wishlist)
    fun getWishList(): Flow<List<Wishlist>>
    fun getWishListById(id : Int): Wishlist

    class Base(val wishListDao: WishlistDao): WishListRepository{
        override fun saveWishlist(wishlist: Wishlist) {
            wishListDao.saveWishList(wishlist.toEntity())
        }

        override fun getWishList(): Flow<List<Wishlist>> {
           return  wishListDao.getWishList().map{
                it.map { it.toWishList() }
            }
        }
        override fun getWishListById(id: Int): Wishlist {
            return wishListDao.getWishlistById(id).toWishList()
        }

    }
}