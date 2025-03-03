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

import com.sgale.dragondex.data.database.entities.CharacterEntity
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.characters.mapRace

object CharacterEntityMapper : EntityMapper<List<CharacterModel>, List<CharacterEntity>> {
    override fun asEntity(domain: List<CharacterModel>): List<CharacterEntity> {
        return domain.map { character ->
            CharacterEntity(
                page    = character.page,
                id      = character.id,
                name    = character.name,
                image   = character.image,
                race    = character.race.name,
                ki      = character.ki,
                maxKi   = character.maxKi,
                gender  = character.gender,
                description = character.description,
                affiliation = character.affiliation,
                deletedAt = character.deletedAt
            )
        }
    }

    override fun asDomain(entity: List<CharacterEntity>): List<CharacterModel> {
        return entity.map { characterEntity ->
            CharacterModel(
                page    = characterEntity.page,
                id      = characterEntity.id,
                name    = characterEntity.name,
                image   = characterEntity.image,
                race    = mapRace(characterEntity.race),
                ki      = characterEntity.ki,
                maxKi   = characterEntity.maxKi,
                gender  = characterEntity.gender,
                description = characterEntity.description,
                affiliation = characterEntity.affiliation,
                deletedAt = characterEntity.deletedAt
            )
        }
    }
}

fun List<CharacterModel>.asEntity(): List<CharacterEntity> {
    return CharacterEntityMapper.asEntity(this)
}

fun List<CharacterEntity>?.asDomain(): List<CharacterModel> {
    return CharacterEntityMapper.asDomain(this.orEmpty())
}