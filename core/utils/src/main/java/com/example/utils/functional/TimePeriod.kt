/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.utils.functional

enum class TimePeriod {
    WEEK, MONTH, HALF_YEAR, YEAR;

    fun convertToDays() = when (this) {
        WEEK -> Constants.Date.DAYS_IN_WEEK
        MONTH -> Constants.Date.DAYS_IN_MONTH
        HALF_YEAR -> Constants.Date.DAYS_IN_HALF_YEAR
        YEAR -> Constants.Date.DAYS_IN_YEAR
    }
}