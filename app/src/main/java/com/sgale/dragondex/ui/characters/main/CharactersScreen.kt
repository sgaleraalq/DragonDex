package com.sgale.dragondex.ui.characters.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.core.CharacterCardContent
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateHome: () -> Unit
) {
    val uiState         by viewModel.uiState.collectAsState()
    val charactersList  by viewModel.characters.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            text = stringResource(R.string.characters),
            onBackPressed = { navigateHome() }
        )
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(charactersList?.items?.size ?: 0) { index ->
                val character = charactersList?.items?.get(index)
                if (character != null) {
                    ItemCard(
                        id = character.id,
                        content = {
                            CharacterCardContent(
                                name = character.name,
                                image = character.image
                            )
                        },
                        onItemClicked = { navigateToDetail(it) }
                    )
                }
            }
        }
    }
}