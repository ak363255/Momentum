/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry

class ObserveAppointmentsService(
    val appointmentRepository : AppointmentRepository) {

    fun observeAppointments() : Flow<List<Appointment>> = appointmentRepository.getAppointments()
        .filterIsInstance<AppointmentEvent.AppointmentUpdated>()
        .map { it.appointments }
        .distinctUntilChanged()
        .retry {
            it is ApiException && it.code in 500..599
        }
}