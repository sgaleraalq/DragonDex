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

package com.sgale.dragondex.data.database.entities.mapper

import com.sgale.dragondex.data.database.entities.planets.PlanetEntity
import com.sgale.dragondex.domain.model.planets.Planet

object PlanetEntityMapper : EntityMapper<List<Planet>, List<PlanetEntity>> {
    override fun asEntity(domain: List<Planet>) =
        domain.map {
            PlanetEntity(
                id = it.id,
                name = it.name,
                isDestroyed = it.isDestroyed,
                image = it.image,
                description = it.description,
            )
        }

    override fun asDomain(entity: List<PlanetEntity>) =
        entity.map {
            Planet(
                id = it.id,
                name = it.name,
                isDestroyed = it.isDestroyed,
                image = it.image,
                description = it.description
            )
        }
}

fun List<Planet>.asEntity(): List<PlanetEntity> {
    return PlanetEntityMapper.asEntity(this)
}

fun List<PlanetEntity>.asDomain(): List<Planet> {
    return PlanetEntityMapper.asDomain(this)
}