package com.sgale.dragondex.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sgale.dragondex.domain.model.CharacterModel
import com.sgale.dragondex.domain.model.Transformation
import com.sgale.dragondex.domain.model.Planet

object Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPlanet(planet: Planet?): String? {
        return gson.toJson(planet)
    }

    @TypeConverter
    fun toPlanet(data: String?): Planet? {
        return gson.fromJson(data, Planet::class.java)
    }

    @TypeConverter
    fun fromTransformations(transformations: List<Transformation>?): String {
        return gson.toJson(transformations)
    }

    @TypeConverter
    fun toTransformations(data: String): List<Transformation> {
        val listType = object : TypeToken<List<Transformation>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromCharacters(characters: List<CharacterModel>?): String {
        return gson.toJson(characters)
    }

    @TypeConverter
    fun toCharacters(data: String): List<CharacterModel> {
        val listType = object : TypeToken<List<CharacterModel>>() {}.type
        return gson.fromJson(data, listType)
    }
}
