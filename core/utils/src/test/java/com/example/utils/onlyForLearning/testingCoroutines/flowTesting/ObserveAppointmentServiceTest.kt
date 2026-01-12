/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ObserveAppointmentServiceTest {

    val appointment1 = Appointment(name = "1", date = "1")
    val appointment2 = Appointment(name = "2", date = "2")

    @Test
    fun `should keep only appointment`() = runTest {
        val flow = flowOf(
            AppointmentEvent.AppointmentConfirmed,
            AppointmentEvent.AppointmentUpdated(listOf(appointment1)),
            AppointmentEvent.AppointmentUpdated(listOf(appointment2)),
            AppointmentEvent.AppointmentConfirmed
        )
        val repo = FakeAppointmentRepository(flow)
        val service = ObserveAppointmentsService(repo)
        val result = service.observeAppointments().toList()
        assertEquals(
            listOf(
                listOf(appointment1),
                listOf(appointment2)
            ), result
        )

    }
}