/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.materials

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PinkLightColorScheme = lightColorScheme(
    primary = Color(0xFFE91E63),        // Pink 500
    onPrimary = Color.White,

    primaryContainer = Color(0xFFFFD9E2),
    onPrimaryContainer = Color(0xFF3E001D),

    secondary = Color(0xFFD81B60),      // Darker pink
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFFFD9E2),
    onSecondaryContainer = Color(0xFF3E001D),

    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = Color(0xFFF2DDE3),
    onSurfaceVariant = Color(0xFF514347),

    error = Color(0xFFB3261E),
    onError = Color.White
)

val PinkDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB1C8),
    onPrimary = Color(0xFF5C0030),

    primaryContainer = Color(0xFF8E0045),
    onPrimaryContainer = Color(0xFFFFD9E2),

    secondary = Color(0xFFFFB1C8),
    onSecondary = Color(0xFF5C0030),

    secondaryContainer = Color(0xFF8E0045),
    onSecondaryContainer = Color(0xFFFFD9E2),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE6E1E5),

    surface = Color(0xFF121212),
    onSurface = Color(0xFFE6E1E5),

    surfaceVariant = Color(0xFF514347),
    onSurfaceVariant = Color(0xFFD6C2C8),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410)
)

