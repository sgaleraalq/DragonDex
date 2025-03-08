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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.sgale.dragondex.R

data class DragonDexColors(
    val primary: Color,
    val background: Color,
    val backgroundLight: Color,
    val backgroundDark: Color,
    val absoluteWhite: Color,
    val absoluteBlack: Color,
    val white: Color,
    val white12: Color,
    val white56: Color,
    val white70: Color,
    val black: Color,
    val grayTransparent: Color,
    val orange: Color,
    val green: Color,
    val blue: Color,
    val bluePrimary: Color,
    val blueSecondary: Color,
    val saiyanColor: Color,
    val namekianColor: Color,
    val humanColor: Color,
    val majinColor: Color,
    val friezaRaceColor: Color,
    val jirenRaceColor: Color,
    val androidRaceColor: Color,
    val godColor: Color,
    val angelColor: Color,
    val evilColor: Color,
    val unknownColor: Color,
    val nucleicoColor: Color
) {
    companion object {
        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [DragonDexColors] instance holding our color palette.
         */

        @Composable
        fun defaultDarkColors(): DragonDexColors = DragonDexColors(
            primary = colorResource(R.color.colorPrimary),
            background = colorResource(id = R.color.background_dark),
            backgroundLight = colorResource(id = R.color.background800_dark),
            backgroundDark = colorResource(id = R.color.background900_dark),
            absoluteWhite = colorResource(id = R.color.white),
            absoluteBlack = colorResource(id = R.color.black),
            white = colorResource(id = R.color.white_dark),
            white12 = colorResource(id = R.color.white_12_dark),
            white56 = colorResource(id = R.color.white_56_dark),
            white70 = colorResource(id = R.color.white_70_dark),
            black = colorResource(id = R.color.black_dark),
            grayTransparent = colorResource(id = R.color.grayTransparent),
            orange = colorResource(id = R.color.orange),
            green = colorResource(id = R.color.green),
            blue = colorResource(id = R.color.blue),
            bluePrimary = colorResource(id = R.color.bluePrimary),
            blueSecondary = colorResource(id = R.color.blueSecondary),
            saiyanColor = colorResource(id = R.color.saiyanColor),
            namekianColor = colorResource(id = R.color.namekianColor),
            humanColor = colorResource(id = R.color.humanColor),
            majinColor = colorResource(id = R.color.majinColor),
            friezaRaceColor = colorResource(id = R.color.friezaRaceColor),
            jirenRaceColor = colorResource(id = R.color.jirenRaceColor),
            androidRaceColor = colorResource(id = R.color.androidRaceColor),
            godColor = colorResource(id = R.color.godColor),
            angelColor = colorResource(id = R.color.angelColor),
            evilColor = colorResource(id = R.color.evilColor),
            unknownColor = colorResource(id = R.color.unknownColor),
            nucleicoColor = colorResource(id = R.color.nucleicoColor)
        )

        @Composable
        fun defaultLightColors(): DragonDexColors = DragonDexColors(
            primary = colorResource(R.color.colorPrimary),
            background = colorResource(id = R.color.background),
            backgroundLight = colorResource(id = R.color.background800),
            backgroundDark = colorResource(id = R.color.background900),
            absoluteWhite = colorResource(id = R.color.white),
            absoluteBlack = colorResource(id = R.color.black),
            white = colorResource(id = R.color.white),
            white12 = colorResource(id = R.color.white_12),
            white56 = colorResource(id = R.color.white_56),
            white70 = colorResource(id = R.color.white_70),
            black = colorResource(id = R.color.black),
            grayTransparent = colorResource(id = R.color.grayTransparent),
            orange = colorResource(id = R.color.orange),
            green = colorResource(id = R.color.green),
            blue = colorResource(id = R.color.blue),
            bluePrimary = colorResource(id = R.color.bluePrimary),
            blueSecondary = colorResource(id = R.color.blueSecondary),
            saiyanColor = colorResource(id = R.color.saiyanColor),
            namekianColor = colorResource(id = R.color.namekianColor),
            humanColor = colorResource(id = R.color.humanColor),
            majinColor = colorResource(id = R.color.majinColor),
            friezaRaceColor = colorResource(id = R.color.friezaRaceColor),
            jirenRaceColor = colorResource(id = R.color.jirenRaceColor),
            androidRaceColor = colorResource(id = R.color.androidRaceColor),
            godColor = colorResource(id = R.color.godColor),
            angelColor = colorResource(id = R.color.angelColor),
            evilColor = colorResource(id = R.color.evilColor),
            unknownColor = colorResource(id = R.color.unknownColor),
            nucleicoColor = colorResource(id = R.color.nucleicoColor)
        )
    }
}