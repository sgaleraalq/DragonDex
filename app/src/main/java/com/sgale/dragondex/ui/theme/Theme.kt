package com.sgale.dragondex.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

private val LocalColors = compositionLocalOf<DragonDexColors> {
    error("No colors provided! Make sure to wrap all usages of DragonDexColors in DragonDexTheme.")
}

@Composable
fun DragonDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: DragonDexColors = if (darkTheme){
        DragonDexColors.defaultDarkColors()
    } else {
        DragonDexColors.defaultLightColors()
    },
    background: DragonDexBackground = DragonDexBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background
    ) {
        Box(
            modifier = Modifier.background(background.color)
        ) {
            content()
        }
    }
}

object DragonDexTheme {

    val colors: DragonDexColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val background: DragonDexBackground
        @Composable
        @ReadOnlyComposable
        get() = LocalBackgroundTheme.current

}