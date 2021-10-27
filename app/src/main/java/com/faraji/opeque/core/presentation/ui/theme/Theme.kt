package com.faraji.opeque.core.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkPurple,
    background = White,
    onBackground = DarkGray,
    onPrimary = White,
    surface = MediumGray,
    onSurface = LightGray
)

private val LightColorPalette = lightColors(
    primary = DarkPurple,
    background = White,
    onBackground = DarkGray,
    onPrimary = White,
    surface = MediumGray,
    onSurface = LightGray
)

@Composable
fun OpequeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}