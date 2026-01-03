/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.utils.functional

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class TimeRange(
    @Serializable(DateSerializer::class) val from: Date,
    @Serializable(DateSerializer::class) val to: Date
)