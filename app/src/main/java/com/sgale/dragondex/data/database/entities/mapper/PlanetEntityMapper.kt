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

import com.sgale.dragondex.data.database.entities.PlanetEntity
import com.sgale.dragondex.domain.model.Planet

object PlanetEntityMapper : EntityMapper<Planet, PlanetEntity> {
    override fun asEntity(domain: Planet) =
        PlanetEntity(
            id = domain.id,
            name = domain.name,
            isDestroyed = domain.isDestroyed,
            image = domain.image,
            description = domain.description,
        )

    override fun asDomain(entity: PlanetEntity) =
        Planet(
            id = entity.id,
            name = entity.name,
            isDestroyed = entity.isDestroyed,
            image = entity.image,
            description = entity.description
        )
}

fun List<Planet>.asEntity(): List<PlanetEntity> {
    return map { PlanetEntityMapper.asEntity(it) }
}

fun List<PlanetEntity>.asDomain(): List<Planet> {
    return map { PlanetEntityMapper.asDomain(it) }
}

fun Planet.asEntity(): PlanetEntity {
    return PlanetEntityMapper.asEntity(this)
}

fun PlanetEntity.asDomain(): Planet {
    return PlanetEntityMapper.asDomain(this)
}