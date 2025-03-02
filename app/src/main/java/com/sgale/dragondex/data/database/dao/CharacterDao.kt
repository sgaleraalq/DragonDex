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
import androidx.room.Query
import com.sgale.dragondex.data.database.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters_table ORDER BY id DESC")
    suspend fun getAllCharacters() : List<CharacterEntity>

    @Insert
    suspend fun insertAllCharacters(characters: List<CharacterEntity>)
}