package com.sgale.dragondex.data.network.response.planets

import com.google.gson.annotations.SerializedName
import com.sgale.dragondex.domain.model.planets.Planet

data class PlanetResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("isDestroyed") val isDestroyed: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("deletedAt") val deletedAt: String?
) {
    fun toDomain() =
        Planet(
            id = id,
            name = name,
            isDestroyed = isDestroyed,
            description = description,
            image = image
        )
}
