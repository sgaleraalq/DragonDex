package com.sgale.dragondex.ui.core


sealed class Route (val route: String) {
    data object Home : Route("launch_screen"){
        fun createRoute() = "launch_screen"
    }

    data object Characters : Route("characters_screen"){
        fun createRoute() = "characters_screen"
    }

    data object CharacterDetail : Route("character_detail_screen/{id}"){
        fun createRoute(id: Int) = "character_detail_screen/$id"
    }

    data object Planets : Route("planets_screen"){
        fun createRoute() = "planets_screen"
    }

    data object PlanetDetailScreen : Route("planet_detail_screen/{id}"){
        fun createRoute(id: String) = "planet_detail_screen/$id"
    }
}