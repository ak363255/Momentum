/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.viewmodel.processor

import com.example.domain.models.schedule.Schedule
import com.example.domain.models.schedule.TimeTask
import com.example.domain.models.settings.TaskSettings
import com.example.domain.models.settings.ViewToggleStatus
import com.example.impl.domain.interactors.ScheduleInteractor
import com.example.impl.domain.interactors.SettingsInteractor
import com.example.impl.domain.interactors.TimeShiftInteractor
import com.example.impl.domain.model.HomeFailures
import com.example.impl.presentation.mappers.schedule.ScheduleDomainToUiMapper
import com.example.impl.presentation.mappers.schedule.mapToDomain
import com.example.impl.presentation.model.schedule.TimeTaskUi
import com.example.impl.presentation.viewmodel.contract.HomeAction
import com.example.impl.presentation.viewmodel.contract.HomeEffect
import com.example.utils.functional.Constants
import com.example.utils.functional.Either
import com.example.utils.platform.viemodel.work.FlowWorkProcessor
import com.example.utils.platform.viemodel.work.WorkCommand
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Date

internal interface ScheduleWorkProcessor :
    FlowWorkProcessor<ScheduleWorkCommand, HomeAction, HomeEffect> {
    class Base(
        private val settingInteractor: SettingsInteractor,
        private val scheduleInteractor: ScheduleInteractor,
        private val timeShiftInteractor: TimeShiftInteractor,
        private val scheduleDomainToUiMapper: ScheduleDomainToUiMapper
    ) : ScheduleWorkProcessor {
        override suspend fun doWork(command: ScheduleWorkCommand): Flow<Either<HomeAction, HomeEffect>> {
            return when (command) {
                is ScheduleWorkCommand.ChangeTaskDoneState -> changeTaskDoneStateWork(
                    command.date,
                    command.key
                )

                is ScheduleWorkCommand.ChangeTaskViewStatus -> changeTaskViewStatusWork(command.status)
                is ScheduleWorkCommand.CreateSchedule -> createScheduleWork(command.date)
                is ScheduleWorkCommand.LoadScheduleByDate -> loadScheduleByDate(date = command.date)
                ScheduleWorkCommand.SetUpSettings -> setUpSettings()
                is ScheduleWorkCommand.TimeTaskShiftDown -> timeTaskShiftDownWork(command.task)
                is ScheduleWorkCommand.TimeTaskShiftUp -> timeTaskShiftUpWork(command.task)
            }
        }

        private fun timeTaskShiftUpWork(task: TimeTaskUi): Flow<Either<HomeAction, HomeEffect>> =
            flow {
                val result = timeShiftInteractor.shiftUpTimeTask(
                    task.mapToDomain(),
                    Constants.Date.SHIFT_MINUTE_VALUE
                )
                when (result) {
                    is Either.Left<HomeFailures> -> emit(Either.Right(HomeEffect.ShowError(result.data)))
                    is Either.Right<List<TimeTask>> -> {
                        //TODO update notification time as well -> will be implemented later

                    }
                }

            }

        private fun timeTaskShiftDownWork(task: TimeTaskUi): Flow<Either<HomeAction, HomeEffect>> =
            flow {
                val shiftDownValue = Constants.Date.SHIFT_MINUTE_VALUE
                val result =
                    timeShiftInteractor.shiftDownTimeTask(task.mapToDomain(), shiftDownValue)
                when (result) {
                    is Either.Left<HomeFailures> -> emit(Either.Right(HomeEffect.ShowError(result.data)))
                    is Either.Right<TimeTask> -> {
                        //TODO update notification time as well -> will be implemented later
                    }
                }

            }

        private fun changeTaskViewStatusWork(status: ViewToggleStatus): Flow<Either<HomeAction, HomeEffect>> =
            flow {
                val result = settingInteractor.fetchTaskSettings().first()
                when (result) {
                    is Either.Left<HomeFailures> -> emit(Either.Right(HomeEffect.ShowError(result.data)))
                    is Either.Right<TaskSettings> -> {
                        val updatedSettings = result.data.copy(viewToggleStatus = status)
                        settingInteractor.updateTaskSettings(updatedSettings).let {
                            when (it) {
                                is Either.Left<HomeFailures> -> emit(
                                    Either.Right(
                                        HomeEffect.ShowError(
                                            it.data
                                        )
                                    )
                                )

                                else -> {}
                            }
                        }

                    }
                }

            }

        private suspend fun createScheduleWork(date: Date): Flow<Either<HomeAction, HomeEffect>> =
            flow {
                val result = scheduleInteractor.createSchedule(date)
                when (result) {
                    is Either.Left<HomeFailures> -> emit(Either.Right(HomeEffect.ShowError(result.data)))
                    is Either.Right<Unit> -> {}
                }
            }


        private suspend fun changeTaskDoneStateWork(
            date: Date,
            key: Long
        ): Flow<Either<HomeAction, HomeEffect>> {
            return flow {
                val result = scheduleInteractor.fetchScheduleByDate(date.time).first()
                when (result) {
                    is Either.Left<HomeFailures> -> emit(Either.Right(HomeEffect.ShowError(result.data)))
                    is Either.Right<Schedule?> -> {
                        if (result.data != null) {
                            val timeTask = result.data!!.timeTask.first { it.key == key }
                            val updatedTimeTask = timeTask.copy(isCompleted = !timeTask.isCompleted)
                            val index = result.data!!.timeTask.indexOf(timeTask)
                            val timeTasks = result.data!!.timeTask.toMutableList()
                            timeTasks[index] = updatedTimeTask
                            val updatedSchedule: Schedule = result.data!!.copy(timeTask = timeTasks)
                            scheduleInteractor.updateSchedule(updatedSchedule).let {
                                when (it) {
                                    is Either.Left<HomeFailures> -> emit(
                                        Either.Right(
                                            HomeEffect.ShowError(
                                                it.data
                                            )
                                        )
                                    )

                                    is Either.Right<Unit> -> emit(
                                        Either.Left(
                                            HomeAction.UpdateSchedule(
                                                scheduleDomainToUiMapper.map(updatedSchedule)
                                            )
                                        )
                                    )

                                }
                            }
                        }

                    }
                }
            }
        }

        private suspend fun loadScheduleByDate(date: Date): Flow<Either<HomeAction, HomeEffect>> {
            return scheduleInteractor.fetchScheduleByDate(date.time)
                .map<Either<HomeFailures, Schedule?>, Either<HomeAction, HomeEffect>> {
                    when (it) {
                        is Either.Left<HomeFailures> -> Either.Right(HomeEffect.ShowError(it.data))
                        is Either.Right<Schedule?> -> {
                            if (it.data != null) {
                                Either.Left(
                                    HomeAction.UpdateSchedule(
                                        timeTaskUi = scheduleDomainToUiMapper.map(it.data!!)
                                    )
                                )
                            } else {
                                Either.Left(
                                    HomeAction.SetUpEmptySchedule(
                                        date = date,
                                        scheduleStatus = null
                                    )
                                )
                            }

                        }
                    }
                }
        }

        private suspend fun setUpSettings(): Flow<Either<HomeAction, HomeEffect>> {
            return settingInteractor.fetchTaskSettings().map {
                when (it) {
                    is Either.Left<HomeFailures> -> Either.Right(HomeEffect.ShowError(it.data))
                    is Either.Right<TaskSettings> -> Either.Left(data = HomeAction.SetUpSettings(it.data))
                }
            }


        }

    }


}


internal sealed class ScheduleWorkCommand : WorkCommand {
    data class CreateSchedule(val date: Date) : ScheduleWorkCommand()
    data object SetUpSettings : ScheduleWorkCommand()
    data class LoadScheduleByDate(val date: Date) : ScheduleWorkCommand()
    data class ChangeTaskDoneState(val date: Date, val key: Long) : ScheduleWorkCommand()
    data class ChangeTaskViewStatus(val status: ViewToggleStatus) : ScheduleWorkCommand()
    data class TimeTaskShiftUp(val task: TimeTaskUi) : ScheduleWorkCommand()
    data class TimeTaskShiftDown(val task: TimeTaskUi) : ScheduleWorkCommand()


}
