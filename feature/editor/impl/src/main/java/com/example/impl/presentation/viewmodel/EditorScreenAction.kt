/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.utils.platform.viemodel.contract.BaseAction

internal sealed interface EditorScreenAction : BaseAction{
    object StartTimeSelected : EditorScreenAction
    object EndTimeSelected : EditorScreenAction
    object DurationSelected : EditorScreenAction
    object CategorySelected : EditorScreenAction
    object SubCategorySelected : EditorScreenAction
    object PrioritySelected : EditorScreenAction
    object CancelClicked : EditorScreenAction
    object SaveClicked  : EditorScreenAction


}