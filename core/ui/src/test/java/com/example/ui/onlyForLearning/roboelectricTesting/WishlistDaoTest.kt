/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class WishlistDaoTest {

    val testDispatcher = UnconfinedTestDispatcher()
    lateinit var wishListDao: WishlistDao
    lateinit var wishListDatabase: WishListDb

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun Setup() {
        Dispatchers.setMain(testDispatcher)
        wishListDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WishListDb::class.java
        ).allowMainThreadQueries().build(
        )
        wishListDao = wishListDatabase.getWishListDao()
    }


    @Test
    fun `get all return empty list`() = runTest(testDispatcher){
        val result = wishListDao.getWishList().firstOrNull()
        assertEquals(emptyList(),result)
    }

    @Test
    fun `save wishlist item and get wishlist item`() = runTest(testDispatcher){
        val wishList = Wishlist(name = "victoria", wishes = listOf("Iphone"),id = 1)
        val entity = wishList.toEntity()
        wishListDao.saveWishList(entity)
        val result = wishListDao.getWishList().first().map { it.id }
        val expected = listOf(entity).map { it.id }
        assertEquals(expected,result)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        wishListDatabase.close()
    }
}