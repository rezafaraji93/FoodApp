package com.faraji.opeque.core.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = DarkPurple,
    background = White,
    onBackground = DarkGray,
    onPrimary = White,
    surface = MediumGray,
    onSurface = LightGray
)

@Composable
fun OpequeTheme(content: @Composable() () -> Unit) {


    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}