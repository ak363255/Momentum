/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.data.repository

import com.example.impl.data.datasources.FeatureScheduleLocalDataSource
import com.example.impl.domain.repository.FeatureScheduleRepository
import java.util.Date

internal class FeatureScheduleRepositoryImpl(
    val featureScheduleLocalDataSource: FeatureScheduleLocalDataSource
) : FeatureScheduleRepository {
    override suspend fun fetchFeatureScheduleDate(): Date? {
        return featureScheduleLocalDataSource.fetchScheduleDate()
    }

    override suspend fun setFeatureScheduleDate(date: Date) {
        featureScheduleLocalDataSource.setScheduleDate(date)
    }
}