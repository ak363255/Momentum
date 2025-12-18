/**
 * @author Amit Kumar on 18/12/25
 */

package com.example.ui.theme.materials

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val RedLightColorScheme = lightColorScheme(
    primary = Color(0xFFD32F2F),        // Red 700
    onPrimary = Color.White,

    primaryContainer = Color(0xFFFFCDD2),
    onPrimaryContainer = Color(0xFF410002),

    secondary = Color(0xFFC62828),      // Darker red
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFFFDAD6),
    onSecondaryContainer = Color(0xFF410001),

    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1C1B1F),

    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = Color(0xFFF5DDDD),
    onSurfaceVariant = Color(0xFF534343),

    error = Color(0xFFB3261E),
    onError = Color.White
)


val RedDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB4AB),
    onPrimary = Color(0xFF690005),

    primaryContainer = Color(0xFF93000A),
    onPrimaryContainer = Color(0xFFFFDAD6),

    secondary = Color(0xFFFFB4A9),
    onSecondary = Color(0xFF680003),

    secondaryContainer = Color(0xFF930006),
    onSecondaryContainer = Color(0xFFFFDAD6),

    background = Color(0xFF121212),
    onBackground = Color(0xFFE6E1E5),

    surface = Color(0xFF121212),
    onSurface = Color(0xFFE6E1E5),

    surfaceVariant = Color(0xFF534343),
    onSurfaceVariant = Color(0xFFD8C2C2),

    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410)
)
