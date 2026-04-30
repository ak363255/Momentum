/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.example.api.navigation.EditorFeatureEntry
import com.example.impl.presentation.editor
import javax.inject.Inject

 internal class EditorFeatureEntryImpl @Inject constructor() : EditorFeatureEntry() {

    override fun NavGraphBuilder.navigate() {
        editor()
    }
}
