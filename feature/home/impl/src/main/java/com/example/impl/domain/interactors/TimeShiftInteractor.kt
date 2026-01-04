/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.impl.domain.interactors

import com.example.domain.models.schedule.TimeTask
import com.example.domain.repository.schedule.ScheduleRepository
import com.example.domain.repository.schedule.TimeTaskRepository
import com.example.impl.common.HomeEitherWrapper
import com.example.impl.domain.model.HomeFailures
import com.example.utils.extensions.isCurrentDay
import com.example.utils.extensions.shiftDay
import com.example.utils.extensions.shiftMinutes
import com.example.utils.extensions.startThisDay
import com.example.utils.extensions.toMinutes
import com.example.utils.functional.Either
import com.example.utils.functional.TimeRange
import com.example.utils.functional.TimeShiftException
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal interface TimeShiftInteractor {
    suspend fun shiftUpTimeTask(
        timeTask: TimeTask,
        shiftValue: Int
    ): Either<HomeFailures, List<TimeTask>>

    suspend fun shiftDownTimeTask(
        timeTask: TimeTask,
        shiftValue: Int
    ): Either<HomeFailures, TimeTask>

    class Base @Inject constructor(
        private val scheduleRepository: ScheduleRepository,
        private val timeTaskRepository: TimeTaskRepository,
        private val homeEitherWrapper: HomeEitherWrapper
    ) : TimeShiftInteractor {
        override suspend fun shiftUpTimeTask(
            timeTask: TimeTask,
            shiftValue: Int
        ): Either<HomeFailures, List<TimeTask>> = homeEitherWrapper.wrap {
            var result: Either<HomeFailures, List<TimeTask>> = Either.Left(HomeFailures.ShiftError)
            val taskDate = timeTask.date.startThisDay()
            val prevDay = taskDate.shiftDay(-1)
            val nextDay = taskDate.shiftDay(1)
            //find all time task in this range [prevDay nextDay]
            val scheduleInGivenRange =
                scheduleRepository.fetchDailyScheduleByRange(TimeRange(prevDay, nextDay)).first()
            //sort all time task by start time
            val sortedTimeTask =
                scheduleInGivenRange.flatMap { it.timeTask }.sortedBy { it.timeRange.from }
            //find first task whose start time is just after timeTask.timeRange.to
            val nextScheduleTimeTask =
                sortedTimeTask.firstOrNull { it.timeRange.from.time > timeTask.timeRange.to.time }
            //check if nextScheduleTimeTask is null or nextScheduleTimeTask.date is not equal to taskDate
            if (nextScheduleTimeTask == null || !nextScheduleTimeTask.date.isCurrentDay(taskDate)) {
                //we found a next task with the different date or no next task
                //increment the current task date by shiftValue and also check it should not cross the current date
                val taskEndAt = timeTask.timeRange.to.shiftDay(1).startThisDay()
                when (taskEndAt.isCurrentDay(timeTask.date)) {
                    true -> {
                        //possible to shift current task
                        //update current task date
                        val updatedTimeTask =
                            timeTask.copy(timeRange = timeTask.timeRange.copy(to = taskEndAt))
                        timeTaskRepository.updateTimeTask(updatedTimeTask)
                        val timeTaskList = timeTaskRepository.fetchAllTimeTaskByDate(timeTask.date)
                        return@wrap timeTaskList
                    }

                    false -> {
                        //cannot shift current task
                        throw TimeShiftException()
                    }
                }

            } else {
                //we found a next task with the same date
                //increment the current task date by shiftValue and also check it should not cross the current date
                //and also increment the next task date by shiftValue and also check it should have min diff of shift value between start and end time
                //is current time task shift interfere with next task
                val taskEndAt = timeTask.timeRange.to.shiftMinutes(shiftValue)
                when (taskEndAt.time >= nextScheduleTimeTask.timeRange.from.time) {
                    true -> {
                        //yes interfering with next task
                        //we need to shift next task as well and also check it should not cross the current date and have min diff of shift value between start and end time
                        val nextTaskStartAt =
                            nextScheduleTimeTask.timeRange.from.shiftMinutes(shiftValue)
                        when ((nextScheduleTimeTask.timeRange.to.time - nextTaskStartAt.time).toMinutes() > shiftValue) {
                            true -> {
                                //possible to shift both task
                                val updatedCurrentTimeTask =
                                    timeTask.copy(timeRange = timeTask.timeRange.copy(to = taskEndAt))
                                val nextUpdateTimeTask = nextScheduleTimeTask.copy(
                                    timeRange = nextScheduleTimeTask.timeRange.copy(from = nextTaskStartAt)
                                )
                                timeTaskRepository.updateTimeTasks(
                                    listOf(
                                        updatedCurrentTimeTask,
                                        nextUpdateTimeTask
                                    )
                                )
                                val timeTaskList =
                                    timeTaskRepository.fetchAllTimeTaskByDate(timeTask.date)
                                return@wrap timeTaskList
                            }

                            false -> {
                                //not possible to shift both task
                                throw TimeShiftException()
                            }
                        }
                    }

                    false -> {
                        //no interfering with next task
                        // simply update the current task date
                        //update current task date
                        val updateSchedule =
                            timeTask.copy(timeRange = timeTask.timeRange.copy(to = taskEndAt))
                        timeTaskRepository.updateTimeTask(updateSchedule)
                        val timeTaskList = timeTaskRepository.fetchAllTimeTaskByDate(timeTask.date)
                        return@wrap timeTaskList
                    }
                }
            }
        }

        override suspend fun shiftDownTimeTask(
            timeTask: TimeTask,
            shiftValue: Int
        ): Either<HomeFailures, TimeTask> = homeEitherWrapper.wrap {
            val taskEndAt = timeTask.timeRange.to.shiftMinutes(-shiftValue)
            when ((taskEndAt.time - timeTask.timeRange.from.time).toMinutes() > shiftValue) {
                true -> {
                    val updatedTimeTask =
                        timeTask.copy(timeRange = timeTask.timeRange.copy(to = taskEndAt))
                    timeTaskRepository.updateTimeTask(updatedTimeTask)
                    updatedTimeTask
                }

                false -> {
                    //not possible to shift  task
                    throw TimeShiftException()
                }
            }
        }

    }
}