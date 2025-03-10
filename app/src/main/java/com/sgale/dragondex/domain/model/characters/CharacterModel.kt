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

package com.sgale.dragondex.domain.model.characters

import com.sgale.dragondex.domain.model.planets.Planet

data class CharacterModel(
    val page: Int,
    val id: Int,
    val name: String,
    val image: String,
    val race: String,
    val ki: String,
    val maxKi: String,
    val gender: String,
    val description: String,
    val affiliation: String,
    val deletedAt: String? = null,
    val planet: Planet? = null,
    val transformations: List<Transformation> = emptyList()
)
