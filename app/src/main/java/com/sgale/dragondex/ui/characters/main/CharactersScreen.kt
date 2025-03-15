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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.CharacterModel
import com.sgale.dragondex.ui.core.AppliedFilter
import com.sgale.dragondex.ui.core.ApplyFiltersButton
import com.sgale.dragondex.ui.core.CharacterCardContent
import com.sgale.dragondex.ui.core.DropDownMenu
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.core.getCharacterRaceColor
import com.sgale.dragondex.ui.theme.DragonDexTheme
import okhttp3.internal.toImmutableList

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    navigateHome: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val charactersList by viewModel.characterList.collectAsStateWithLifecycle()
    val isLastItem by viewModel.isLastItem.collectAsStateWithLifecycle()

    // DropDown
    val racesList by viewModel.racesList.collectAsStateWithLifecycle()
    val affiliationsList by viewModel.affiliationsList.collectAsStateWithLifecycle()
    val selectedRace by viewModel.selectedRace.collectAsStateWithLifecycle()
    val selectedAffiliation by viewModel.selectedAffiliation.collectAsStateWithLifecycle()

    var showFilters by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DragonDexTheme.colors.backgroundDark)
    ) {
        Header(
            text = stringResource(R.string.characters),
            onBackPressed = { navigateHome() },
            onShowFilters = { showFilters = !showFilters }
        )

        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            if (!selectedRace.isNullOrBlank()){
                AppliedFilter(selectedRace!!) { viewModel.changeRace(null) }
            }
            if (!selectedAffiliation.isNullOrBlank()){
                AppliedFilter(selectedAffiliation!!) { viewModel.changeAffiliation(null) }

            }
        }

        if (showFilters) {
            CharacterFilters(
                racesList = racesList,
                affiliationsList = affiliationsList,
                race = selectedRace,
                affiliation = selectedAffiliation,
                onRaceSelected = { viewModel.changeRace(it) },
                onAffiliationSelected = { viewModel.changeAffiliation(it) },
                hideFilters = { showFilters = !showFilters }
            )
        }

        CharactersList(
            charactersList = charactersList.toImmutableList(),
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
    fetchNextCharacters: () -> Unit = {},
    navigateToDetail: (Int) -> Unit = {}
) {
    val filteredChar = charactersList.filter { it.isVisible }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        val threadHold = 2
        itemsIndexed(
            items = filteredChar,
            key = { _, character -> character.name }
        ) { index, character ->
            // Load more items when there are only two items left
            if ((index + threadHold) >= filteredChar.size && uiState != UIState.Loading && !isLastItem) {
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

@Composable
fun CharacterFilters(
    racesList: List<String> = emptyList(),
    affiliationsList: List<String> = emptyList(),
    race: String? = null,
    affiliation: String? = null,
    onRaceSelected: (String?) -> Unit = {},
    onAffiliationSelected: (String?) -> Unit = {},
    hideFilters: () -> Unit = {}
) {
    var isRaceExpanded by rememberSaveable { mutableStateOf(false) }
    var isAffiliationsExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedRace by rememberSaveable { mutableStateOf(race) }
    var selectedAffiliation by rememberSaveable { mutableStateOf(affiliation) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 8.dp)
    ) {
        DropDownMenu(
            modifier = Modifier.weight(1f),
            dropDownTitle = stringResource(R.string.race),
            menuExpanded = isRaceExpanded,
            selectedItem = selectedRace ?: "",
            items = racesList,
            onChangeDisplay = { isRaceExpanded = !isRaceExpanded },
            onFilterApplied = {
                isRaceExpanded = !isRaceExpanded
                selectedRace = it
            }
        )
        DropDownMenu(
            modifier = Modifier.weight(1f),
            dropDownTitle = stringResource(R.string.affiliation),
            menuExpanded = isAffiliationsExpanded,
            selectedItem = selectedAffiliation ?: "",
            items = affiliationsList,
            onChangeDisplay = { isAffiliationsExpanded = !isAffiliationsExpanded },
            onFilterApplied = {
                isAffiliationsExpanded = !isAffiliationsExpanded
                selectedAffiliation = it
            }
        )
    }

    ApplyFiltersButton {
        hideFilters()
        onRaceSelected(selectedRace)
        onAffiliationSelected(selectedAffiliation)
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharactersMainPreview() {
    DragonDexTheme {
        Column {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
            ) {
                AppliedFilter("Test") {}
            }
            CharacterFilters(
                racesList = listOf(
                    "Test", "Test", "Test"
                ),
                affiliationsList = listOf(
                    "Test", "Test", "Test"
                ),
                race = "Test",
                affiliation = "Test"
            )
            CharactersList(
                charactersList = PreviewUtils.mockCharacterList(),
                isLastItem = false,
                uiState = UIState.Success,
            )
        }
    }
}