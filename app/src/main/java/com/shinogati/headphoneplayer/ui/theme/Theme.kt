package com.shinogati.headphoneplayer.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColorScheme(
    primary = Teal200,
    onPrimary = White,
//    primaryVariant = TealA700,
    secondary = Teal200,
    error = RedErrorLight,
    background = Black2,
    onBackground = Gray300,
    surface = Black3,
    onSurface = Gray200,
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColorScheme(
    primary = Teal200,
    onPrimary = White,
//    primaryVariant = TealA700,
    secondary = Teal200,
    onSecondary = Black1,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = BackGroundColor,
    onBackground = onBackGround,
    surface = SurfaceColor,
    onSurface = onSurface,
)

@Composable
fun HeadphonePlayerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = NunitoTypography,
        shapes = Shapes,
        content = content
    )
}