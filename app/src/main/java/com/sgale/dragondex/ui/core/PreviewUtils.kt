package com.sgale.dragondex.ui.core

import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.characters.Transformation
import com.sgale.dragondex.domain.model.planets.Planet

object PreviewUtils {
    private fun mockCharacter(id: Int) = CharacterModel(
        page = 1,
        id = 1,
        name = "Goku $id",
        image = "https://dragonball-api.com/characters/goku_normal.webp",
        race = "Saiyan",
        ki = "60.000.000",
        maxKi = "90 Septillion",
        gender = "Male",
        description = "Test description Test description Test description Test description Test description Test description Test description Test description Test description Test description ",
        affiliation = "Z Fighter"
    )

    fun mockPlanet(id: Int) = Planet(
        id = id,
        name = "Vegeta",
        isDestroyed = true,
        image = "https://dragonball-api.com/planetas/Planeta_Vegeta_en_Dragon_Ball_Super_Broly.webp",
        description = "Test description Test description Test description Test description Test description Test description Test description Test description Test description Test description ",
        characters = List(5) { mockCharacter(it) }
    )

    fun mockCharacterList() = List(10) {
        mockCharacter(it)
    }

    fun mockPlanetList() = List(10) {
        mockPlanet(it)
    }

    fun mockTransformation() = Transformation(
        id = 1,
        name = "Goku SSJ",
        image = "https://dragonball-api.com/transformaciones/goku_ssj.webp",
        ki = "3 Billion",
        deletedAt = null
    )

    fun mockCharacterInfo() = CharacterInfo(
        id = 1,
        name = "Goku",
        image = "https://dragonball-api.com/characters/goku_normal.webp",
        race = "Saiyan",
        ki = "60.000.000",
        maxKi = "90 Septillion",
        gender = "Male",
        description = "Test description Test description Test description Test description Test description Test description Test description Test description Test description Test description ",
        affiliation = "Z Fighter",
        planet = mockPlanet(1),
        transformations = List(5) {
            mockTransformation()
        }
    )
}