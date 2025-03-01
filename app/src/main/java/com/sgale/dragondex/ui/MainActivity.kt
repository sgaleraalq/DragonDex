package com.sgale.dragondex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sgale.dragondex.ui.characters.detail.CharacterDetailScreen
import com.sgale.dragondex.ui.characters.main.CharactersScreen
import com.sgale.dragondex.ui.core.Route
import com.sgale.dragondex.ui.core.Route.*
import com.sgale.dragondex.ui.launch.LaunchScreen
import com.sgale.dragondex.ui.planets.PlanetsScreen
import com.sgale.dragondex.ui.theme.DragonDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
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
        modifier = modifier,
        navController = navController,
        startDestination = LaunchScreen.route
    ) {
        composable(
            route = LaunchScreen.route
        ) {
            LaunchScreen(
                navigateToCharacters = { navigateTo(CharactersScreen.createRoute()) },
                navigateToPlanets = { navigateTo(PlanetsScreen.createRoute()) }
            )
        }

        composable(
            route = CharactersScreen.route
        ) {
            CharactersScreen(
                navigateToDetail = { id -> navigateTo(CharacterDetailScreen.createRoute(id)) }
            )
        }

        composable(
            route = CharacterDetailScreen.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            CharacterDetailScreen(
                id = it.arguments?.getInt("id") ?: 0
            )
        }

        composable(
            route = PlanetsScreen.route
        ) {
            PlanetsScreen()
        }
    }
}