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
import com.sgale.dragondex.data.network.response.CharacterListResponse
import com.sgale.dragondex.data.network.services.DragonBallApiService
import com.sgale.dragondex.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dragonBallApiService: DragonBallApiService
): Repository {

    override suspend fun getAllCharacters(): CharacterListResponse? {
        runCatching { dragonBallApiService.getAllCharacters() }
            .onFailure { Log.i("sgalera", "Ha ocurrido un error ${it.message}") }
            .onSuccess { Log.i("sgalera", it.toString()) }
        return null
    }
}