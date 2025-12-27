/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.impl.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.impl.presentation.theme.token.LocalHomeIcons
import com.example.impl.presentation.theme.token.LocalHomeStrings
import com.example.impl.presentation.theme.token.baseHomeIcons
import com.example.impl.presentation.theme.token.fetchHomeStrings
import com.example.ui.theme.tokens.LocalMomentumLanguage

@Composable
internal fun HomeTheme(
    content: @Composable () -> Unit
) {
    val language = LocalMomentumLanguage.current
    val homeStrings = fetchHomeStrings(language)
    val homeIcons = baseHomeIcons
    CompositionLocalProvider(
        LocalHomeIcons provides homeIcons,
        LocalHomeStrings provides homeStrings
    ) {
        content()
    }

}