/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.flow.Flow
import java.lang.Exception


sealed class AppointmentEvent {
    data object AppointmentConfirmed : AppointmentEvent()
    data object AppointmentCancelled : AppointmentEvent()
    data class AppointmentUpdated(val appointments: List<Appointment>) : AppointmentEvent()
}

data class ApiException(val code: Int) : Exception()

data class Appointment(val name: String, val date: String)
interface AppointmentRepository {

    fun getAppointments(): Flow<AppointmentEvent>
}

class FakeAppointmentRepository(
    val flow : Flow<AppointmentEvent>
) : AppointmentRepository{
    override fun getAppointments(): Flow<AppointmentEvent> = flow
}