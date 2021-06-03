/*
 * Created by Ramprasad Ranganathan on 02/06/21, 2:07 PM
 * Copyright (c) 2021. All rights reserved
 * Last modified 01/06/21, 2:45 PM
 */
package dev.ramprasad.bloom.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = pink100,
    secondary = pink900,
    background = Color.White,
    surface = white850,
    onPrimary = gray,
    onSecondary = Color.White,
    onBackground = gray,
    onSurface = gray
)

private val DarkColorPalette = darkColors(
    primary = green900,
    secondary = green300,
    background = gray,
    surface = white150,
    onPrimary = Color.White,
    onSecondary = gray,
    onBackground = Color.White,
    onSurface = white850
)

@Composable
fun BloomTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
