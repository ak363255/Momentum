/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.domain.repository

import java.util.Date

interface  FeatureScheduleRepository {
    suspend fun fetchFeatureScheduleDate() : Date?
    suspend fun setFeatureScheduleDate(date : Date)
}