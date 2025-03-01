package com.sgale.dragondex.ui.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgale.dragondex.ui.characters.components.CharacterCard

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val uiState         by viewModel.uiState.collectAsState()
    val charactersList  by viewModel.characters.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            items(charactersList?.items?.size ?: 0) { index ->
                val character = charactersList?.items?.get(index)
                CharacterCard(
                    character = character!!,
                ) {
                    /* TODO NAVIGATE TO DETAIL */
                }
            }
        }
    }
}