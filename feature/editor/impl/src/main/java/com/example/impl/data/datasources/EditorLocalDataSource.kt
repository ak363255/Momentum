/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.data.datasources

import com.example.impl.domain.entity.EditModel
import javax.inject.Inject

internal interface EditorLocalDataSource {
    fun fetchEditModel() : EditModel
    fun saveEditModel(model : EditModel)

    class Base @Inject constructor() : EditorLocalDataSource{
        private var currentValue : EditModel? = null
        override fun fetchEditModel(): EditModel {
            return checkNotNull(currentValue)
        }

        override fun saveEditModel(model: EditModel) {
            currentValue = model
        }

    }

}