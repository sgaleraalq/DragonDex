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

package com.sgale.dragondex.data

import androidx.annotation.WorkerThread
import com.sgale.dragondex.data.database.dao.CharacterDao
import com.sgale.dragondex.data.database.dao.PlanetsDao
import com.sgale.dragondex.data.database.entities.mapper.asDomain
import com.sgale.dragondex.data.database.entities.mapper.asEntity
import com.sgale.dragondex.data.network.Dispatcher
import com.sgale.dragondex.data.network.DragonDexAppDispatchers
import com.sgale.dragondex.data.network.response.mappers.asDomain
import com.sgale.dragondex.data.network.services.DragonBallClient
import com.sgale.dragondex.domain.Repository
import com.sgale.dragondex.domain.model.CharacterModel
import com.sgale.dragondex.domain.model.Planet
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dragonBallClient: DragonBallClient,
    private val charactersDao: CharacterDao,
    private val planetsDao: PlanetsDao,
    @Dispatcher(DragonDexAppDispatchers.IO) private val ioDispatchers: CoroutineDispatcher
) : Repository {

    @WorkerThread
    override suspend fun fetchCharacters(
        page: Int,
        race: String?,
        affiliation: String?,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit,
        onLastCall: () -> Unit
    ) = flow {
        var characters = charactersDao.getCharactersList(page, race, affiliation).asDomain()
        if (characters.isEmpty()) {
            /**
             * If we can't get characters from database, we take it from API and insert it into database
             */
            val response = runCatching { dragonBallClient.fetchCharacters(page, race, affiliation) }.onFailure {onError(it.message ?: "Unknown Error")}.getOrNull()
            if (response != null) {
                if (response.links.next.isNullOrBlank()) {
                    onLastCall()
                }
                characters = response.items.map { characterResponse ->
                    characterResponse.asDomain().copy(page = page)
                }
                charactersDao.insertCharactersList(characters.asEntity())
                emit(charactersDao.getAllCharactersList(page, race, affiliation).asDomain())
            } else {
                onError("API ERROR")
            }
        } else {
            /**
             * If we have characters in database, we just emit them
             */
            emit(charactersDao.getAllCharactersList(page, race, affiliation).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatchers).distinctUntilChanged()


    override suspend fun fetchCharacterById(id: Int): CharacterModel? {
        val charFromDb = charactersDao.getCharacterById(id)
        runCatching { dragonBallClient.getCharacter(id) }
            .onSuccess {
                val character = it.asDomain().copy(page = charFromDb?.page ?: 0)
                charactersDao.insertCharacterById(character.asEntity())
                return character
            }
            .onFailure { return charFromDb?.asDomain() }
        return null
    }


    /**
     * Planets
     */
    override suspend fun fetchPlanets(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit,
        onLastCall: () -> Unit
    ) = flow {
        var planets = planetsDao.getPlanetsList(page).asDomain()
        if (planets.isEmpty()) {
            val response = runCatching { dragonBallClient.fetchPlanets(page) }.onFailure { onError(it.message ?: "Unknown Error") }.getOrNull()

            if (response != null) {
                if (response.links.next.isNullOrBlank()) {
                    onLastCall()
                }

                planets = response.items.map { it.asDomain().copy(page = page) }
                planetsDao.insertPlanetsList(planets.asEntity())
                emit(planetsDao.getAllPlanets(page).asDomain())
            } else {
                onError("API ERROR")
            }
        } else {
            emit(planetsDao.getAllPlanets(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatchers)

    override suspend fun fetchPlanetById(id: Int): Planet? {
        val planetFromDb = planetsDao.getPlanetInfoById(id)
        runCatching { dragonBallClient.getPlanet(id) }
            .onSuccess {
                val planet = it.asDomain()
                planetsDao.insertPlanetById(planet.asEntity())
                return planet
            }
            .onFailure { return planetFromDb?.asDomain() }
        return null
    }
}