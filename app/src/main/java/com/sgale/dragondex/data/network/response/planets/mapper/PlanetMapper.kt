package com.sgale.dragondex.data.network.response.planets.mapper

import com.sgale.dragondex.data.network.response.ResponseMapper
import com.sgale.dragondex.data.network.response.characters.mapper.asDomain
import com.sgale.dragondex.data.network.response.planets.PlanetResponse
import com.sgale.dragondex.domain.model.planets.Planet

object PlanetMapper: ResponseMapper<Planet, PlanetResponse> {
    override fun asDomain(response: PlanetResponse) = Planet(
        id          = response.id,
        name        = response.name,
        isDestroyed = response.isDestroyed,
        image       = response.image,
        description = response.description,
        characters  = response.characters?.map { it.asDomain() } ?: emptyList()
    )
}

fun PlanetResponse.asDomain(): Planet {
    return PlanetMapper.asDomain(this)
}
