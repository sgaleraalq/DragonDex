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

import com.sgale.dragondex.data.database.entities.CharacterInfoEntity
import com.sgale.dragondex.domain.model.characters.CharacterInfo

object CharacterInfoEntityMapper : EntityMapper<CharacterInfo, CharacterInfoEntity>{
    override fun asEntity(domain: CharacterInfo) =
        CharacterInfoEntity(
            id      = domain.id,
            name    = domain.name,
            image   = domain.image,
            race    = domain.race,
            ki      = domain.ki,
            maxKi   = domain.maxKi,
            gender  = domain.gender,
            description = domain.description,
            affiliation = domain.affiliation,
            deletedAt = domain.deletedAt,
            originPlanet = domain.originPlanet,
            transformations = domain.transformations
        )

    override fun asDomain(entity: CharacterInfoEntity) =
        CharacterInfo(
            id      = entity.id,
            name    = entity.name,
            image   = entity.image,
            race    = entity.race,
            ki      = entity.ki,
            maxKi   = entity.maxKi,
            gender  = entity.gender,
            description = entity.description,
            affiliation = entity.affiliation,
            deletedAt = entity.deletedAt,
            originPlanet = entity.originPlanet,
            transformations = entity.transformations
        )
}

fun CharacterInfo.asEntity(): CharacterInfoEntity {
    return CharacterInfoEntityMapper.asEntity(this)
}

fun CharacterInfoEntity.asDomain(): CharacterInfo {
    return CharacterInfoEntityMapper.asDomain(this)
}