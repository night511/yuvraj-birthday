package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CinematicDarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    secondary = PrimaryBlue,
    tertiary = NeonCyan,
    background = DeepBlack,
    surface = SurfaceBlack,
    onPrimary = DeepBlack,
    onSecondary = GlowingWhite,
    onTertiary = GlowingWhite,
    onBackground = GlowingWhite,
    onSurface = GlowingWhite,
    surfaceVariant = GlassBlack,
    onSurfaceVariant = GlowingWhite,
    outline = DarkBorder
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = CinematicDarkColorScheme,
        typography = Typography,
        content = content
    )
}

