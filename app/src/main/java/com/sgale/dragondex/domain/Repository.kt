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

package com.sgale.dragondex.domain

import com.sgale.dragondex.domain.model.characters.CharacterListModel
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.planets.PlanetsListModel

interface Repository {
    suspend fun getAllCharacters(): CharacterListModel?
    suspend fun getCharacter(id: Int): CharacterModel?
    suspend fun getAllPlanets(): PlanetsListModel?
}