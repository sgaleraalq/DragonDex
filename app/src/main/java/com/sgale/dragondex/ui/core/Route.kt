package com.sgale.dragondex.ui.core


sealed class Route (val route: String) {
    data object MainScreen : Route("main_screen"){
        fun createRoute() = "main_screen"
    }

    data object DetailScreen : Route("detail_screen/{id}"){
        fun createRoute(id: String) = "detail_screen/$id"
    }
}