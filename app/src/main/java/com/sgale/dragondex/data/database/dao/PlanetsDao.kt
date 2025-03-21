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

package com.sgale.dragondex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sgale.dragondex.data.database.entities.PlanetEntity

@Dao
interface PlanetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetsList(planets: List<PlanetEntity>)

    @Query("SELECT * FROM PlanetEntity WHERE page =:page")
    suspend fun getPlanetsList(page: Int): List<PlanetEntity>

    @Query("SELECT * FROM PlanetEntity WHERE page <=:page")
    suspend fun getAllPlanets(page: Int): List<PlanetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlanetById(planet: PlanetEntity)

    @Query("SELECT * FROM PlanetEntity WHERE id = :id")
    suspend fun getPlanetInfoById(id: Int): PlanetEntity?
}