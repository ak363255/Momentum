/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.data.datasources.setting

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.managers.CoroutineManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject

class SettingsDataBaseCallback @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val coroutineManager: CoroutineManager
) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        coroutineManager.runOnBackground(CoroutineScope(coroutineDispatcher)) {
            db.execSQL(
                """
        INSERT INTO ThemeSettings 
        (id, colors_type, language, theme_colors)
        VALUES (0, 'RED', 'ENG', 'DEFAULT')
        """
            )
        }
    }
}