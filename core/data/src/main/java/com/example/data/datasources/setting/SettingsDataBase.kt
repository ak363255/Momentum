/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.data.datasources.setting

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.models.setting.ThemeSettingsEntity

@Database(entities = [ThemeSettingsEntity::class], version = 1)
abstract class SettingsDataBase : RoomDatabase() {
    abstract fun getThemeSettingsDao(): ThemeSettingsDao


    companion object {
        fun create(context: Context,callback: SettingsDataBaseCallback) =
            Room.databaseBuilder(context, SettingsDataBase::class.java, name = "Settings_Database")
                .addCallback(callback)
                .build()
    }
}