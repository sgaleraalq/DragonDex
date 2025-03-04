package com.sgale.dragondex.data.network.response.characters.mapper

import com.sgale.dragondex.data.network.response.ResponseMapper
import com.sgale.dragondex.data.network.response.characters.CharacterInfoResponse
import com.sgale.dragondex.data.network.response.characters.CharacterResponse
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.characters.mapRace

object CharacterMapper : ResponseMapper <CharacterModel, CharacterResponse> {
    override fun asDomain(response: CharacterResponse) =
        CharacterModel(
            id = response.id,
            name = response.name,
            image = response.image,
            race = mapRace(response.race),
            ki = response.ki,
            maxKi = response.maxKi,
            gender = response.gender,
            description = response.description,
            affiliation = response.affiliation
        )
}

object CharacterInfoMapper : ResponseMapper<CharacterInfo, CharacterInfoResponse> {
    override fun asDomain(response: CharacterInfoResponse) =
        CharacterInfo(
            id = response.id,
            name = response.name,
            image = response.image,
            race = mapRace(response.race),
            ki = response.ki,
            maxKi = response.maxKi,
            gender = response.gender,
            description = response.description,
            affiliation = response.affiliation,
            originPlanet = null,//response.originPlanet.asDomain(), // TODO
            transformations = null, //response.transformations.asDomain() // TODO
        )
}

fun CharacterResponse.asDomain(): CharacterModel {
    return CharacterMapper.asDomain(this)
}

fun CharacterInfoResponse.asDomain(): CharacterInfo {
    return CharacterInfoMapper.asDomain(this)
}