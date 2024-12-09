package com.application.isyara.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.application.isyara.ui.theme.DarkBackground
import com.application.isyara.ui.theme.DarkOnPrimary
import com.application.isyara.ui.theme.DarkPrimary
import com.application.isyara.ui.theme.DarkSecondary
import com.application.isyara.ui.theme.DarkTextPrimary
import com.application.isyara.ui.theme.LightBackground
import com.application.isyara.ui.theme.LightOnPrimary
import com.application.isyara.ui.theme.LightPrimary
import com.application.isyara.ui.theme.LightSecondary
import com.application.isyara.ui.theme.LightTextPrimary

// Light Theme Color Scheme
private val LightColorPalette = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    surface = LightBackground,
    onPrimary = LightOnPrimary,
    onSecondary = LightTextPrimary,
    onSurface = LightTextPrimary
)

// Dark Theme Color Scheme
private val DarkColorPalette = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    surface = DarkBackground,
    onPrimary = DarkOnPrimary,
    onSecondary = DarkTextPrimary,
    onSurface = DarkTextPrimary
)
@Composable
fun IsyaraTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
