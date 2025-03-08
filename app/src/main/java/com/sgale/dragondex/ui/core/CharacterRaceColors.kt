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

package com.sgale.dragondex.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sgale.dragondex.ui.theme.DragonDexTheme

@Composable
fun getCharacterRaceColor(type: String): Color {
    return when (type) {
        "Saiyan"            -> DragonDexTheme.colors.saiyanColor
        "Namekian"          -> DragonDexTheme.colors.namekianColor
        "Human"             -> DragonDexTheme.colors.humanColor
        "Majin"             -> DragonDexTheme.colors.majinColor
        "Frieza Race"       -> DragonDexTheme.colors.friezaRaceColor
        "Jiren Race"        -> DragonDexTheme.colors.jirenRaceColor
        "Android"           -> DragonDexTheme.colors.androidRaceColor
        "God"               -> DragonDexTheme.colors.godColor
        "Angel"             -> DragonDexTheme.colors.angelColor
        "Evil"              -> DragonDexTheme.colors.evilColor
        "Unknown"           -> DragonDexTheme.colors.unknownColor
        "Nucleico Benigno"  -> DragonDexTheme.colors.nucleicoColor
        "Nucleico"          -> DragonDexTheme.colors.nucleicoColor
        else -> DragonDexTheme.colors.unknownColor
    }
}