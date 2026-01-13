/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.onlyForLearning.testingCoroutines.flowTesting.AppointmentRepository
import kotlinx.coroutines.launch

class DetailViewmodel(val wishListRepository: WishListRepository) : ViewModel() {


    fun saveWishList(wishList: Wishlist){
        viewModelScope.launch {
            wishListRepository.saveWishlist(wishList)
        }
    }
}