/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class DetailViewmodelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    lateinit var detailViewmodel: DetailViewmodel
    lateinit var wishListRepository: WishListRepository
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        wishListRepository = mock()
        detailViewmodel = DetailViewmodel(wishListRepository)
    }


    @Test
    fun `Save new item call to database`() = runTest(testDispatcher){
        val wishList = Wishlist(name =  "victoria", wishes = listOf("Iphone"),id = 1)
         detailViewmodel.saveWishList(wishList)
        verify(wishListRepository).saveWishlist(any())
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}