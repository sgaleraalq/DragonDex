package com.sgale.dragondex.ui.core


sealed class Route (val route: String) {
    data object LaunchScreen : Route("launch_screen"){
        fun createRoute() = "launch_screen"
    }

    data object CharactersScreen : Route("characters_screen"){
        fun createRoute() = "characters_screen"
    }

    data object CharacterDetailScreen : Route("character_detail_screen/{id}"){
        fun createRoute(id: String) = "character_detail_screen/$id"
    }
}