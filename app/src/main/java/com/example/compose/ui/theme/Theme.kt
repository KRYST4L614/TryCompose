package com.example.compose.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primaryDarkTheme,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = backgroundDarkTheme,
    surface = surfaceDarkTheme,
    surfaceTint = backgroundDarkTheme,
    onSurface = onSurfaceDarkTheme,
)

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    onPrimary = White,
    background = background,
    surface = surface,
    surfaceTint = surface,
    onSurface = Black,
)

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}