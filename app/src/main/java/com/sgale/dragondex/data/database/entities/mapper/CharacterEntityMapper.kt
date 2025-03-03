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
import com.sgale.dragondex.domain.model.characters.Character

object CharacterEntityMapper : EntityMapper<List<Character>, List<CharacterEntity>> {
    override fun asEntity(domain: List<Character>): List<CharacterEntity> {
        return domain.map { character ->
            CharacterEntity(
                page    = character.page,
                name    = character.name,
                url     = character.url
            )
        }
    }

    override fun asDomain(entity: List<CharacterEntity>): List<Character> {
        return entity.map { characterEntity ->
            Character(
                page    = characterEntity.page,
                name    = characterEntity.name,
                url     = characterEntity.url
            )
        }
    }
}

fun List<Character>.asEntity(): List<CharacterEntity> {
    return CharacterEntityMapper.asEntity(this)
}

fun List<CharacterEntity>?.asDomain(): List<Character> {
    return CharacterEntityMapper.asDomain(this.orEmpty())
}