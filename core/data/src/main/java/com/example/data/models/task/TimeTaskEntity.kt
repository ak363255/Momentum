/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("timeTasks")
data class TimeTaskEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("key")
    val key : Long,
    @ColumnInfo("dailyScheduleDate", index = true)
    val dailyScheduleDate : Long,
    @ColumnInfo("nextScheduleDate", defaultValue = "null")
    val nextScheduleDate : Long?,
    @ColumnInfo("startTime")
    val startTime : Long,
    @ColumnInfo("endTime")
    val endTime : Long,
    @ColumnInfo("createAt")
    val createdAt : Long? ,
    @ColumnInfo("mainCategoryId")
    val mainCategoryId : Int,
    @ColumnInfo("subCategoryId")
    val subCategoryId : Int?,
    @ColumnInfo("isCompleted", defaultValue = "1")
    val isCompleted : Boolean ,
    @ColumnInfo("priority")
    val priority : Int,
    @ColumnInfo("isEnableNotification")
    val isEnableNotification : Boolean,
    @ColumnInfo("note")
    val note : String?,
    @ColumnInfo("isConsiderInStatistics")
    val isConsiderInStatistics : Boolean,
    @ColumnInfo("fifteenMinBeforeNotify")
    val fifteenMinBeforeNotify : Boolean = false,
    @ColumnInfo("oneHourBeforeNotify")
    val oneHourBeforeNotify : Boolean = false,
    @ColumnInfo("twoHourBeforeNotify")
    val twoHourBeforeNotify : Boolean = false,
    @ColumnInfo("dayBeforeNotify")
    val dayBeforeNotify: Boolean = false

)