/**
 * @author Amit Kumar on 01/01/26
 */

package com.example.data.datasources.schedule

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.domain.models.categories.DefaultCategoryType
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.managers.CoroutineManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class ScheduleDatabaseCallback @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val coroutineManager: CoroutineManager
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        coroutineManager.runOnBackground(CoroutineScope(ioDispatcher)) {
            try {
                db.beginTransaction()
                val mainCategories = DefaultCategoryType.values()
                mainCategories.forEachIndexed { index,category ->
                    db.execSQL(
                        """
                    INSERT INTO mainCategory
                    (id,categoryName,defaultCategoryType) values ('$index','${category.name}','${category.name}')
                """.trimIndent()
                    )
                }
                db.setTransactionSuccessful()
            }finally {
                db.endTransaction()

            }

        }
    }
}