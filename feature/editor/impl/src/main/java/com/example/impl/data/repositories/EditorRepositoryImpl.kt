/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.data.repositories

import com.example.impl.data.datasources.EditorLocalDataSource
import com.example.impl.domain.entity.EditModel
import com.example.impl.domain.repositories.EditorRepository
import javax.inject.Inject

internal class EditorRepositoryImpl @Inject constructor(
    private val localDataSource : EditorLocalDataSource
): EditorRepository{
    override fun fetchEditModel(): EditModel = localDataSource.fetchEditModel()

    override fun saveEditModel(model: EditModel) = localDataSource.saveEditModel(model)
}