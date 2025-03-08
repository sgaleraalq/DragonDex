/*
 * Designed and developed by 2025 sgaleraalq (Sergio Galera)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sgale.dragondex.ui.characters.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.ui.core.CharacterCardContent
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.core.getCharacterRaceColor
import com.sgale.dragondex.ui.theme.DragonDexTheme


@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateHome: () -> Unit
) {
    val uiState         by viewModel.uiState.collectAsStateWithLifecycle()
    val charactersList  by viewModel.characterList.collectAsStateWithLifecycle()
    val isLastItem      by viewModel.isLastItem.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DragonDexTheme.colors.backgroundDark)
    ) {
        Header(
            text = stringResource(R.string.characters),
            onBackPressed = { navigateHome() }
        )
        Spacer(Modifier.height(8.dp))
        CharactersList(
            charactersList = charactersList,
            isLastItem = isLastItem,
            uiState = uiState,
            fetchNextCharacters = { viewModel.fetchNextCharacters() },
            navigateToDetail = { navigateToDetail(it) }
        )
    }

    if (uiState == UIState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CharactersList(
    charactersList: List<CharacterModel>,
    isLastItem: Boolean,
    uiState: UIState,
    fetchNextCharacters: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        val threadHold = 2
        itemsIndexed(
            items = charactersList,
            key = { _, character -> character.name }
        ) { index, character ->
            // Load more items when there are only two items left
            if ((index + threadHold) >= charactersList.size && uiState != UIState.Loading && !isLastItem) {
                fetchNextCharacters()
            }

            ItemCard(
                id = character.id,
                content = {
                    CharacterCardContent(
                        name = character.name,
                        image = character.image
                    )
                },
                color = getCharacterRaceColor(character.race),
                onItemClicked = { navigateToDetail(it) }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharactersMainPreview() {
    DragonDexTheme {
        CharactersList(
            charactersList = PreviewUtils.mockCharacterList(),
            isLastItem = false,
            uiState = UIState.Success,
            fetchNextCharacters = {},
            navigateToDetail = {}
        )
    }
}