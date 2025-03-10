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

import com.sgale.dragondex.data.database.entities.characters.CharacterEntity
import com.sgale.dragondex.domain.model.characters.CharacterModel

object CharacterEntityMapper : EntityMapper<CharacterModel, CharacterEntity>{
    override fun asEntity(domain: CharacterModel) =
        CharacterEntity(
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
            planet = domain.planet,
            transformations = domain.transformations
        )

    override fun asDomain(entity: CharacterEntity) =
        CharacterModel(
            page    = entity.page,
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
            planet = entity.planet,
            transformations = entity.transformations
        )
}

fun List<CharacterModel>.asEntity(): List<CharacterEntity> {
    return map { CharacterEntityMapper.asEntity(it) }
}

fun List<CharacterEntity>.asDomain(): List<CharacterModel> {
    return map { CharacterEntityMapper.asDomain(it) }
}

fun CharacterModel.asEntity(): CharacterEntity {
    return CharacterEntityMapper.asEntity(this)
}

fun CharacterEntity.asDomain(): CharacterModel {
    return CharacterEntityMapper.asDomain(this)
}