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

package com.sgale.dragondex.domain.model.characters

import androidx.compose.ui.graphics.Color
import com.sgale.dragondex.domain.model.characters.CharacterRace.*
import com.sgale.dragondex.ui.theme.androidRaceColor
import com.sgale.dragondex.ui.theme.angelColor
import com.sgale.dragondex.ui.theme.evilColor
import com.sgale.dragondex.ui.theme.friezaRaceColor
import com.sgale.dragondex.ui.theme.godColor
import com.sgale.dragondex.ui.theme.humanColor
import com.sgale.dragondex.ui.theme.jirenRaceColor
import com.sgale.dragondex.ui.theme.majinColor
import com.sgale.dragondex.ui.theme.namekianColor
import com.sgale.dragondex.ui.theme.nucleicoColor
import com.sgale.dragondex.ui.theme.saiyanColor
import com.sgale.dragondex.ui.theme.unknownColor
import java.util.UnknownFormatConversionException


/**
 * Painting characters background thanks to these classes
 */

sealed class CharacterRace(
    val name: String,
    val color: Color
) {
    data object Saiyan:             CharacterRace("Saiyan", saiyanColor)
    data object Namekian:           CharacterRace("Namekian", namekianColor)
    data object Human:              CharacterRace("Human", humanColor)
    data object Majin:              CharacterRace("Majin", majinColor)
    data object FriezaRace:         CharacterRace("Frieza Race", friezaRaceColor)
    data object JirenRace:          CharacterRace("Jiren Race", jirenRaceColor)
    data object Android:            CharacterRace("Android", androidRaceColor)
    data object God:                CharacterRace("God", godColor)
    data object Angel:              CharacterRace("Angel", angelColor)
    data object Evil:               CharacterRace("Evil", evilColor)
    data object Unknown:            CharacterRace("Unknown", unknownColor)
    data object NucleicoBenigno:    CharacterRace("Nucleico Benigno", nucleicoColor)
    data object Nucleico:           CharacterRace("Nucleico", nucleicoColor)
}


fun mapRace(race: String): CharacterRace {
    return when (race) {
        "Saiyan"            -> Saiyan
        "Namekian"          -> Namekian
        "Human"             -> Human
        "Majin"             -> Majin
        "Frieza Race"       -> FriezaRace
        "Jiren Race"        -> JirenRace
        "Android"           -> Android
        "God"               -> God
        "Angel"             -> Angel
        "Evil"              -> Evil
        "Unknown"           -> Unknown
        "Nucleico Benigno"  -> NucleicoBenigno
        "Nucleico"          -> Nucleico
        else -> Unknown
    }
}
