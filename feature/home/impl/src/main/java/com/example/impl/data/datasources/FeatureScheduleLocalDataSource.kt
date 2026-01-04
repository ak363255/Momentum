/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.data.datasources

import java.util.Date
import javax.inject.Inject

internal interface FeatureScheduleLocalDataSource {

    suspend fun fetchScheduleDate(): Date?

    fun setScheduleDate(date: Date?)

    class Base @Inject constructor() :
        FeatureScheduleLocalDataSource {

        private var scheduleDate: Date? = null

        override suspend fun fetchScheduleDate(): Date? {
            return scheduleDate
        }

        override fun setScheduleDate(date: Date?) {
            this.scheduleDate = date
        }
    }
}