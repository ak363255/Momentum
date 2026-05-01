/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.impl.presentation.template.TemplateUi
import com.example.utils.platform.viemodel.contract.BaseEvent

internal sealed interface EditorEvent : BaseEvent {
    object Init : EditorEvent
    object CreateTemplate : EditorEvent
    data class ApplyTemplate(val template: TemplateUi) : EditorEvent


}