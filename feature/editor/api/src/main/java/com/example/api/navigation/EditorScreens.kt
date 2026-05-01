/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.api.navigation

import com.example.domain.models.schedule.TimeTask
import com.example.domain.models.template.Template

sealed class EditorScreens {
    data class Editor(
        val timeTask: TimeTask?,
        val template : Template?,
        val undefinedTaskId : Long? = null
    ) : EditorScreens()
}