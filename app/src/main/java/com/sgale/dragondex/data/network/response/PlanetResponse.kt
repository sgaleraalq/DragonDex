package com.sgale.dragondex.data.network.response

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("id")           val id: Int,
    @SerializedName("name")         val name: String,
    @SerializedName("isDestroyed")  val isDestroyed: Boolean,
    @SerializedName("description")  val description: String,
    @SerializedName("image")        val image: String,
    @SerializedName("deletedAt")    val deletedAt: String?,
    @SerializedName("characters")   val characters: List<CharacterResponse>?
)