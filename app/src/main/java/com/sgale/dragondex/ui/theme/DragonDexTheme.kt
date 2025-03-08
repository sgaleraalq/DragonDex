/*
 * Designed and developed by 2025 sgaleraalq (Sergio Galera)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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