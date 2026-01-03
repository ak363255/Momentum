/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.schedule

import java.util.Date

data class Schedule(
    val date : Long,
    val timeTask : List<TimeTask>,
    val status : DailScheduleStatus,

)