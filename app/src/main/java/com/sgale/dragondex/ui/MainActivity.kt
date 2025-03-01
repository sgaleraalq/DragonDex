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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sgale.dragondex.ui.characters.CharactersScreen
import com.sgale.dragondex.ui.core.Route
import com.sgale.dragondex.ui.launch.LaunchScreen
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
    fun navigateTo() {
        navController.navigate(Route.CharactersScreen.createRoute())
        // TODO
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.LaunchScreen.route
    ) {
        composable(
            Route.LaunchScreen.route
        ) {
            LaunchScreen(
                navigateToCharacters = {
                    navigateTo()
                }
            )
        }

        composable(
            Route.CharactersScreen.route
        ) {
            CharactersScreen()
        }

    }
}