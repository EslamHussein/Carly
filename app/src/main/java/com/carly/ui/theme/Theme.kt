package com.carly.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme()

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary, // Primary color
    onPrimary = FontDark, // Font color on primary (light)


    secondary = YellowAccent, // Accent color (secondary color)
    onSecondary = FontLight, // Font color on secondary

    background = BackgroundDark, // Background color (light)
    onBackground = FontLight, // Font color on background (dark)

    surface = FontLight, // Surface color (similar to background)
    onSurface = BackgroundDark, // Font color on surface

    primaryContainer = TextFieldBackground,
    onPrimaryContainer = BackgroundDark, // Font color on primary container

    secondaryContainer = YellowAccent.copy(alpha = 0.1f), // Lighter version of secondary for containers
    onSecondaryContainer = BackgroundDark, // Font color on secondary container

    surfaceVariant = FontDark, // Use the light grey background color for surface variants
    onSurfaceVariant = BackgroundLight // Font color on surface variants
)

@Composable
fun CarlyTheme(
    content: @Composable () -> Unit
) {
    // TODO apply dark theme based on system settings

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}