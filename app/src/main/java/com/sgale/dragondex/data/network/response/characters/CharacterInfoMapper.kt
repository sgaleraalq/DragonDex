package com.sgale.dragondex.data.network.response.characters

import com.sgale.dragondex.data.network.response.ResponseMapper
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.domain.model.characters.CharacterRace
import com.sgale.dragondex.domain.model.characters.mapRace

//
//object CharacterInfoMapper : ResponseMapper<List<CharacterInfo>, List<CharacterInfoResponse>> {
//    override fun asDomain(response: List<CharacterInfoResponse>) =
//        response.map { characterInfoResponse ->
//            CharacterInfo(
//                id = characterInfoResponse.id,
//                name = characterInfoResponse.name,
//                image = characterInfoResponse.image,
//                race = mapRace(characterInfoResponse.race),
//                ki = characterInfoResponse.ki,
//                maxKi = characterInfoResponse.maxKi,
//                gender = characterInfoResponse.gender,
//                description = characterInfoResponse.description,
//                affiliation = characterInfoResponse.affiliation
//            )
//        }
//}
//
//fun List<CharacterInfoResponse>?.asDomain(): List<CharacterInfo> {
//    return CharacterInfoMapper.asDomain(this ?: emptyList())
//}


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
            affiliation = response.affiliation
        )
}

fun CharacterInfoResponse.asDomain(): CharacterInfo {
    return CharacterInfoMapper.asDomain(this)
}