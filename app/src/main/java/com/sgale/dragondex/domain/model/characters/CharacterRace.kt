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

enum class RaceType {
    SAIYAN, NAMEKIAN, HUMAN, MAJIN, FRIEZA_RACE, JIREN_RACE, ANDROID, GOD, ANGEL, EVIL, UNKNOWN, NUCLEICO_BENIGNO, NUCLEICO
}

sealed class CharacterRace(
    val race: RaceType,
    val color: Color
) {
    data object Saiyan:             CharacterRace(RaceType.SAIYAN, saiyanColor)
    data object Namekian:           CharacterRace(RaceType.NAMEKIAN, namekianColor)
    data object Human:              CharacterRace(RaceType.HUMAN, humanColor)
    data object Majin:              CharacterRace(RaceType.MAJIN, majinColor)
    data object FriezaRace:         CharacterRace(RaceType.FRIEZA_RACE, friezaRaceColor)
    data object JirenRace:          CharacterRace(RaceType.JIREN_RACE, jirenRaceColor)
    data object Android:            CharacterRace(RaceType.ANDROID, androidRaceColor)
    data object God:                CharacterRace(RaceType.GOD, godColor)
    data object Angel:              CharacterRace(RaceType.ANGEL, angelColor)
    data object Evil:               CharacterRace(RaceType.EVIL, evilColor)
    data object Unknown:            CharacterRace(RaceType.UNKNOWN, unknownColor)
    data object NucleicoBenigno:    CharacterRace(RaceType.NUCLEICO_BENIGNO, nucleicoColor)
    data object Nucleico:           CharacterRace(RaceType.NUCLEICO, nucleicoColor)
}


fun mapRace(race: String): CharacterRace {
    return when (race) {
        "Saiyan"    -> Saiyan
        "Namekian"  -> Namekian
        "Human"     -> Human
        "Majin"     -> Majin
        "Frieza Race"    -> FriezaRace
        "Jiren Race"     -> JirenRace
        "Android"   -> Android
        "God"       -> God
        "Angel"     -> Angel
        "Evil"      -> Evil
        "Unknown"   -> Unknown
        "Nucleico Benigno" -> NucleicoBenigno
        "Nucleico"  -> Nucleico
        else -> Unknown
    }
}
