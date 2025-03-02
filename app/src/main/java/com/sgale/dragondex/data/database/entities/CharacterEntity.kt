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

package com.sgale.dragondex.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sgale.dragondex.domain.model.characters.CharacterRace

@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey                         val id: Int,
    @ColumnInfo(name = "name")          val name: String,
    @ColumnInfo(name = "image")         val image: String,
    @ColumnInfo(name = "race")          val race: CharacterRace,
    @ColumnInfo(name = "ki")            val ki: String,
    @ColumnInfo(name = "maxKi")         val maxKi: String,
    @ColumnInfo(name = "gender")        val gender: String,
    @ColumnInfo(name = "description")   val description: String,
    @ColumnInfo(name = "affiliation")   val affiliation: String,
    @ColumnInfo(name = "deletedAt")     val deletedAt: String? = null
)