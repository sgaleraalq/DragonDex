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

import com.sgale.dragondex.data.network.response.CharacterResponse
import com.sgale.dragondex.data.network.response.core.MainResponse
import com.sgale.dragondex.data.network.response.PlanetResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallApiService {

    @GET("characters")
    suspend fun fetchCharacters(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int,
        @Query("race") race: String? = null,
        @Query("affiliation") affiliation: String? = null
    ): MainResponse<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse

    @GET("planets")
    suspend fun fetchPlanets(
        @Query("page") page: Int
    ): MainResponse<PlanetResponse>

    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id: Int): PlanetResponse
}