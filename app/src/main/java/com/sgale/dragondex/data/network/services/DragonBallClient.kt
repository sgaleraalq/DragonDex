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

package com.sgale.dragondex.data.network.services

import com.sgale.dragondex.data.network.response.characters.CharacterInfoResponse
import com.sgale.dragondex.data.network.response.characters.CharacterResponse
import com.sgale.dragondex.data.network.response.core.MainResponse
import com.sgale.dragondex.data.network.response.planets.PlanetResponse
import javax.inject.Inject

class DragonBallClient @Inject constructor(
    private val apiService: DragonBallApiService
) {
    companion object {
        const val LIMIT = 10
    }

    suspend fun fetchCharacters(page: Int): MainResponse<CharacterResponse> {
        return apiService.fetchCharacters(
            limit = LIMIT,
            page = page
        )
    }

    suspend fun getCharacter(id: Int): CharacterInfoResponse {
        return apiService.getCharacter(id)
    }

    suspend fun fetchPlanets(page: Int): MainResponse<PlanetResponse> {
        return apiService.fetchPlanets(
            page = page
        )
    }
}