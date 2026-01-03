/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.schedule

enum class TaskPriority {
    STANDARD, MEDIUM,MAX;

    fun isImportant () = this != STANDARD

}