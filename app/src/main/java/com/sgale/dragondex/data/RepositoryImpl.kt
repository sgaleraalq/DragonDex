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

import android.util.Log
import androidx.annotation.WorkerThread
import com.sgale.dragondex.data.database.dao.CharacterDao
import com.sgale.dragondex.data.database.entities.mapper.asDomain
import com.sgale.dragondex.data.database.entities.mapper.asEntity
import com.sgale.dragondex.data.network.Dispatcher
import com.sgale.dragondex.data.network.DragonDexAppDispatchers
import com.sgale.dragondex.data.network.response.characters.mapper.asDomain
import com.sgale.dragondex.data.network.services.DragonBallApiService
import com.sgale.dragondex.data.network.services.DragonBallClient
import com.sgale.dragondex.domain.Repository
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.domain.model.planets.PlanetsListModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dragonBallApiService: DragonBallApiService,
    private val dragonBallClient: DragonBallClient,
    private val charactersDao: CharacterDao,
    @Dispatcher(DragonDexAppDispatchers.IO) private val ioDispatchers: CoroutineDispatcher
) : Repository {

    @WorkerThread
    override suspend fun fetchCharacters(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        var characters = charactersDao.getCharactersList(page = page).asDomain()
        if (characters.isEmpty()){
            /**
             * If we can't get characters from database, we take it from API and insert it into database
             */
            val response = runCatching { dragonBallClient.fetchCharacters(page = page) }.onFailure { onError(it.message ?: "Unknown Error")}.getOrNull()
            if (response != null) {
                characters = response.items.map { characterResponse -> characterResponse.asDomain().copy(page = page) }
                charactersDao.insertCharactersList(characters.asEntity())
                emit(charactersDao.getAllCharactersList(page).asDomain())
            } else { onError("API ERROR") }
        } else {
            /**
             * If we have characters in database, we just emit them
             */
            emit(charactersDao.getAllCharactersList(page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatchers)


    override suspend fun getCharacter(id: Int): CharacterInfo? {
        runCatching { dragonBallApiService.getCharacter(id) }
            .onSuccess { return null }
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