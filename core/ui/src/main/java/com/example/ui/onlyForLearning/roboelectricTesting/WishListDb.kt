/**
 * @author Amit Kumar on 13/01/26
 */

package com.example.ui.onlyForLearning.roboelectricTesting

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WishlistEntity::class], version = 1)
abstract class WishListDb : RoomDatabase(){
    abstract fun getWishListDao(): WishlistDao
    companion object{
        private val db: WishListDb? = null
        fun create(context : Context): WishListDb{
            if(db != null)return db
            synchronized(this){
                return Room
                    .databaseBuilder(context = context, klass = WishListDb::class.java,name = "wishlist.db")
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}