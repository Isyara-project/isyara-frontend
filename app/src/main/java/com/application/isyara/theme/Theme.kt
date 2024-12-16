package com.application.isyara.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.application.isyara.R
import com.application.isyara.ui.theme.*

private val LightColorPalette = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    tertiary = AccentColor,
    surface = LightBackground,
    onPrimary = LightOnPrimary,
    onSecondary = LightTextPrimary,
    onTertiary = TextColorPrimary,
    onSurface = LightTextPrimary,
    background = LightBackground,
    onBackground = LightTextPrimary
)

private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkAccentColor,
    surface = DarkBackground,
    onPrimary = DarkOnPrimary,
    onSecondary = DarkTextPrimary,
    onTertiary = DarkTextPrimary,
    onSurface = DarkTextSecondary,
    background = DarkBackground,
    onBackground = DarkTextPrimary
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

@Composable
fun IsyaraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
