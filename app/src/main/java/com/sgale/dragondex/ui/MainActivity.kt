package com.sgale.dragondex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sgale.dragondex.ui.characters.detail.CharacterDetailScreen
import com.sgale.dragondex.ui.characters.main.CharactersScreen
import com.sgale.dragondex.ui.core.Route.CharacterDetail
import com.sgale.dragondex.ui.core.Route.Characters
import com.sgale.dragondex.ui.core.Route.Home
import com.sgale.dragondex.ui.core.Route.Planets
import com.sgale.dragondex.ui.home.HomeScreen
import com.sgale.dragondex.ui.planets.PlanetsScreen
import com.sgale.dragondex.ui.theme.DragonDexTheme
import com.sgale.dragondex.ui.theme.bluePrimary
import com.sgale.dragondex.ui.theme.blueSecondary
import com.sgale.dragondex.ui.theme.dragonBallOrange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController  = rememberSystemUiController()
            val navController       = rememberNavController()

            systemUiController.setStatusBarColor(color = dragonBallOrange)
            DragonDexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Content(
    modifier: Modifier,
    navController: NavHostController
) {
    fun navigateTo(
        destination: String,
    ) {
        navController.navigate(destination) {
            launchSingleTop = true
            restoreState = false
        }
    }

    NavHost(
        modifier = modifier.background(
            brush = Brush.radialGradient(
                colors = listOf(bluePrimary, blueSecondary),
                center = Offset(x = 0.5f, y = 1f),
                radius = 2500f
            )
        ),
        navController = navController,
        startDestination = Home.route
    ) {
        composable(
            route = Home.route
        ) {
            HomeScreen(
                navigateToCharacters = { navigateTo(Characters.createRoute()) },
                navigateToPlanets = { navigateTo(Planets.createRoute()) }
            )
        }

        composable(
            route = Characters.route
        ) {
            CharactersScreen(
                navigateToDetail = { id -> navigateTo(CharacterDetail.createRoute(id)) },
                navigateHome = { navigateTo(Home.createRoute()) }
            )
        }

        composable(
            route = CharacterDetail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            CharacterDetailScreen(
                id = it.arguments?.getInt("id") ?: 0,
                navigateBack = { navigateTo(Characters.createRoute()) }
            )
        }

        composable(
            route = Planets.route
        ) {
            PlanetsScreen(
                navigateHome = { navigateTo(Home.createRoute()) }
            )
        }
    }
}