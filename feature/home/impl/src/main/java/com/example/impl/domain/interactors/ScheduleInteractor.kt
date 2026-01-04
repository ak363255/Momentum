/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.domain.interactors

import com.example.domain.common.ScheduleStatusChecker
import com.example.domain.models.schedule.Schedule
import com.example.domain.repository.schedule.ScheduleRepository
import com.example.impl.common.HomeEitherWrapper
import com.example.impl.domain.model.HomeFailures
import com.example.impl.domain.repository.FeatureScheduleRepository
import com.example.utils.functional.Either
import com.example.utils.managers.DateManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

internal interface ScheduleInteractor {

    fun fetchScheduleByDate(date: Long): Flow<Either<HomeFailures, Schedule?>>
    suspend fun createSchedule(requireDate: Date): Either<HomeFailures, Unit>
    suspend fun updateSchedule(schedule: Schedule): Either<HomeFailures, Unit>
    suspend fun fetchFeatureScheduleDate(): Date?
    suspend fun setFeatureScheduleDate(date: Date)

     class Base @Inject constructor(
        private val scheduleRepository: ScheduleRepository,
        private val dateManager: DateManager,
        private val statusChecker: ScheduleStatusChecker,
        private val homeEitherWrapper: HomeEitherWrapper,
        private val featureScheduleRepository: FeatureScheduleRepository
    ) : ScheduleInteractor {
        override fun fetchScheduleByDate(date: Long): Flow<Either<HomeFailures, Schedule?>> {
            //Schedule contains the list of time tasks for the given date
            return homeEitherWrapper.wrapFlow {
                scheduleRepository.fetchScheduleByDate(date)
            }
        }


        override suspend fun createSchedule(requireDate: Date): Either<HomeFailures, Unit> {
            //creates an empty schedule for the given date
            //time tasks are empty
            val currentDate = dateManager.fetchCurrentDate()
            val schedule = Schedule(
                date = requireDate.time,
                timeTask = emptyList(),
                status = statusChecker.fetchStatus(requireDate, currentDate)
            )
            return homeEitherWrapper.wrap {
                scheduleRepository.createSchedule(listOf(schedule))
            }
        }

        override suspend fun updateSchedule(schedule: Schedule): Either<HomeFailures, Unit> {
            return homeEitherWrapper.wrap {
                scheduleRepository.updateSchedule(schedule)
            }
        }

        override suspend fun fetchFeatureScheduleDate(): Date? {
            return featureScheduleRepository.fetchFeatureScheduleDate()
        }

        override suspend fun setFeatureScheduleDate(date: Date) {
            featureScheduleRepository.setFeatureScheduleDate(date)
        }

    }
}