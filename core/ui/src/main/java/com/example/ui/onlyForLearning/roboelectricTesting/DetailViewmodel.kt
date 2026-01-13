/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailViewmodel(val wishlistDao: WishlistDao) : ViewModel() {


    fun saveWishList(wishList: Wishlist){
        viewModelScope.launch {
            wishlistDao.save(wishList)
        }
    }
}