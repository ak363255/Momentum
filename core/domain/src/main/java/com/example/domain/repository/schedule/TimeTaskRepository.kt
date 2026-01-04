/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.domain.repository.schedule

import com.example.domain.models.schedule.TimeTask
import java.util.Date

interface TimeTaskRepository {
    suspend fun updateTimeTasks(timeTasks : List<TimeTask>)
    suspend fun addTimeTasks(timeTasks : List<TimeTask>)
    suspend fun fetchAllTimeTaskByDate(date : Date) : List<TimeTask>
    suspend fun updateTimeTask(timeTask: TimeTask)
    suspend fun removeTimeTaskByKey(keys : List<Long>)

}