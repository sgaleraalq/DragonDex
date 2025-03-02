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

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.ui.core.CharacterCardContent
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.theme.primaryDark

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateHome: () -> Unit
) {
    val context = LocalContext.current
    val uiState             by viewModel.uiState.collectAsState()
    val toastMsg            by viewModel.toastMsg.collectAsState()
    val charactersList      by viewModel.characterList.collectAsState()
//    val charactersList      by viewModel.characters.collectAsState()

    LaunchedEffect(toastMsg) {
        if (toastMsg != null) { Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show() }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(primaryDark)
    ) {
        Header(
            text = stringResource(R.string.characters),
            onBackPressed = { navigateHome() }
        )
        Spacer(Modifier.height(8.dp))
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
                if ((index + threadHold) >= charactersList.size && uiState != UIState.Loading) {
                    viewModel.fetchNextCharacters()
                }

                ItemCard(
                    id = character.id,
                    content = {
                        CharacterCardContent(
                            name = character.name,
                            image = character.image
                        )
                    },
                    color = character.race.color,
                    onItemClicked = { navigateToDetail(it) }
                )
            }
        }
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