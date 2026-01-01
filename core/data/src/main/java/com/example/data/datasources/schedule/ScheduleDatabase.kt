/**
 * @author Amit Kumar on 01/01/26
 */

package com.example.data.datasources.schedule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.categories.MainCategoryEntity
import com.example.data.models.categories.SubCategoryEntity
import com.example.data.models.schedules.DailyScheduleEntity
import com.example.data.models.task.TimeTaskEntity



@Database(
    entities = [
        DailyScheduleEntity::class,
        TimeTaskEntity::class,
        MainCategoryEntity::class,
        SubCategoryEntity::class],
    version = 1
)
abstract class ScheduleDatabase : RoomDatabase() {
    abstract fun getScheduleDao(): ScheduleDao

    companion object {
        fun create(
            context: Context,
            scheduleDatabaseCallback: ScheduleDatabaseCallback
        ): ScheduleDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = ScheduleDatabase::class.java,
                name = "schedule_database"
            )
                .addCallback(callback = scheduleDatabaseCallback)
                .build()

        }
    }
}
