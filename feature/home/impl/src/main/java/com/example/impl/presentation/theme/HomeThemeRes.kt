/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.impl.presentation.theme

import androidx.compose.runtime.Composable
import com.example.impl.presentation.theme.token.HomeCoreStrings
import com.example.impl.presentation.theme.token.HomeIcons
import com.example.impl.presentation.theme.token.LocalHomeIcons
import com.example.impl.presentation.theme.token.LocalHomeStrings

internal object HomeThemeRes {
    internal val homeStrings: HomeCoreStrings
        @Composable get() = LocalHomeStrings.current

    internal val homeIcons: HomeIcons
        @Composable get() = LocalHomeIcons.current


}