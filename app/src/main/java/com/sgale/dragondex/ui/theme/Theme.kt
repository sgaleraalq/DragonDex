package com.sgale.dragondex.ui.theme

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
    // dragonBallOrange

)

private val LightColorScheme = lightColorScheme(
    dragonBallOrange = primary
)

private val LocalColors = compositionLocalOf<DragonDexColors> {
    error("No colors provided! Make sure to wrap all usages of DragonDexColors in DragonDexTheme.")
}

@Composable
fun DragonDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: DragonDexColors = DragonDexTheme.colors,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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