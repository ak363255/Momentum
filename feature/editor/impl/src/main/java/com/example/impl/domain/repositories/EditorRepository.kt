/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.domain.repositories

import com.example.impl.domain.entity.EditModel

internal interface  EditorRepository {
    fun fetchEditModel() : EditModel
    fun saveEditModel(model : EditModel)
}