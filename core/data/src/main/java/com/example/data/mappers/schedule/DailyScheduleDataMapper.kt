/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.data.mappers.schedule

import com.example.data.models.schedules.DailyScheduleEntity
import com.example.domain.models.schedule.Schedule

fun Schedule.mapToData() = DailyScheduleEntity(date = date)