/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.EditorFeatureApi
import com.example.api.navigation.EditorFeatureStarterApi
import javax.inject.Inject

internal class EditorFeatureApiImpl @Inject constructor(
    private val editorFeatureStarterApi: EditorFeatureStarterApi
): EditorFeatureApi {
    override fun getEditorFeatureNavigationApi(): EditorFeatureStarterApi = editorFeatureStarterApi
}