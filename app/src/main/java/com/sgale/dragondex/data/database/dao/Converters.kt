package com.sgale.dragondex.data.database.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sgale.dragondex.domain.model.characters.OriginPlanet
import com.sgale.dragondex.domain.model.characters.Transformation

object Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromOriginPlanet(originPlanet: OriginPlanet?): String? {
        return gson.toJson(originPlanet)
    }

    @TypeConverter
    fun toOriginPlanet(data: String?): OriginPlanet? {
        return gson.fromJson(data, OriginPlanet::class.java)
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
}
