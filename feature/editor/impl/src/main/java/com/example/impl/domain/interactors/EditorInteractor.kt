/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.domain.interactors

import com.example.impl.data.repositories.EditorRepositoryImpl
import com.example.impl.domain.entity.EditModel
import javax.inject.Inject

internal interface EditorInteractor {
    fun fetchEditModel() : EditModel
    fun sendEditModel(model : EditModel)
     class Base @Inject constructor(
        private val editorRepository: EditorRepositoryImpl
    ) : EditorInteractor{
        override fun fetchEditModel(): EditModel = editorRepository.fetchEditModel()

        override fun sendEditModel(model: EditModel) = editorRepository.saveEditModel(model)

    }
}