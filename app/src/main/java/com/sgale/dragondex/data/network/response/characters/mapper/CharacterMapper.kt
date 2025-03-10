package com.sgale.dragondex.data.network.response.characters.mapper

import com.sgale.dragondex.data.network.response.ResponseMapper
import com.sgale.dragondex.data.network.response.characters.CharacterResponse
import com.sgale.dragondex.data.network.response.characters.TransformationResponse
import com.sgale.dragondex.data.network.response.planets.mapper.asDomain
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.characters.Transformation

object CharacterMapper : ResponseMapper <CharacterModel, CharacterResponse> {
    override fun asDomain(response: CharacterResponse) =
        CharacterModel(
            page = 0,
            id = response.id,
            name = response.name,
            image = response.image,
            race = response.race,
            ki = response.ki,
            maxKi = response.maxKi,
            gender = response.gender,
            description = response.description,
            affiliation = response.affiliation,
            deletedAt = response.deletedAt,
            planet = response.planet.asDomain(),
            transformations = response.transformations.map { it.asDomain() }
        )
}

object TransformationMapper: ResponseMapper<Transformation, TransformationResponse> {
    override fun asDomain(response: TransformationResponse) = Transformation(
        id = response.id,
        name = response.name,
        image = response.image,
        ki = response.ki
    )
}

fun CharacterResponse.asDomain(): CharacterModel {
    return CharacterMapper.asDomain(this)
}

fun TransformationResponse.asDomain(): Transformation {
    return TransformationMapper.asDomain(this)
}