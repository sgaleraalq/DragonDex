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

package com.sgale.dragondex.data.network

import android.util.Log
import com.sgale.dragondex.data.network.services.DragonBallApiService
import com.sgale.dragondex.data.network.services.DragonBallClient
import com.sgale.dragondex.domain.Repository
import com.sgale.dragondex.domain.model.characters.CharacterListModel
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.planets.PlanetsListModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dragonBallApiService: DragonBallApiService,
    private val dragonBallClient: DragonBallClient
): Repository {

    /*
    * Get characters from the API
     */
    override suspend fun fetchCharacters(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): CharacterListModel? {
        onStart()
        runCatching { dragonBallClient.fetchCharacters(page = page) }
            .onSuccess {
                onComplete()
                return it.toDomain()
            }
            .onFailure { onError(it.message) }
        return null
    }

    override suspend fun getCharacter(id: Int): CharacterModel? {
        runCatching { dragonBallApiService.getCharacter(id) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("sgalera", "Ha ocurrido un error ${it.message}") }
        return null
    }

    /*
    * Get planets from the API
     */
    override suspend fun getAllPlanets(): PlanetsListModel? {
        runCatching { dragonBallApiService.getAllPlanets() }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("sgalera", "Ha ocurrido un error ${it.message}") }
        return null
    }
}