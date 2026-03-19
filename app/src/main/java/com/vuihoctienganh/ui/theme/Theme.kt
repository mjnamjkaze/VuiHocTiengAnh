package com.vuihoctienganh.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AccentGreen,
    secondary = AccentTeal,
    tertiary = AccentBlue,
    background = DarkBg,
    surface = DarkSurface,
    surfaceVariant = DarkCard,
    onPrimary = DarkBg,
    onSecondary = DarkBg,
    onTertiary = DarkBg,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    error = WrongRed,
    onError = Color.White
)

@Composable
fun VuiHocTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
