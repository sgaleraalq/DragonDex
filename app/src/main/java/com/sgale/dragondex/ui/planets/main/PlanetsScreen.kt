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

package com.sgale.dragondex.ui.planets.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.DropdownMenu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.Planet
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PlanetCardContent
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.theme.DragonDexTheme

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel = hiltViewModel(),
    navigateHome: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    val uiState     by viewModel.uiState.collectAsStateWithLifecycle()
    val planetsList by viewModel.planetsList.collectAsStateWithLifecycle()
    val isLastItem  by viewModel.isLastItem.collectAsStateWithLifecycle()
    var showFilters by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            text = stringResource(R.string.planets),
            onBackPressed = { navigateHome() },
            onShowFilters = { showFilters = !showFilters }
        )
        if (showFilters) {

        }
        PlanetsList(
            planetsList = planetsList,
            isLastItem = isLastItem,
            uiState = uiState,
            fetchNextPlanets = { viewModel.fetchNextPlanets() },
            navigateToDetail = { navigateToDetail(it) }
        )
    }
}

@Composable
fun PlanetsList(
    planetsList: List<Planet>,
    isLastItem: Boolean,
    uiState: UIState,
    fetchNextPlanets: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        val threadHold = 2
        itemsIndexed(
            items = planetsList,
            key = { _, planet -> planet.id }
        ) { index, planet ->
            // Load more items when there are only two items left
            if ((index + threadHold) >= planetsList.size && uiState != UIState.Loading && !isLastItem) {
                fetchNextPlanets()
            }

            ItemCard(
                id = planet.id,
                content = {
                    PlanetCardContent(
                        name = planet.name,
                        image = planet.image
                    )
                },
                onItemClicked = {
                    navigateToDetail(planet.id)
                } 
            )
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlanetsMainPreview() {
    DragonDexTheme {
        PlanetsList(
            planetsList = PreviewUtils.mockPlanetList(),
            isLastItem = false,
            uiState = UIState.Success,
            fetchNextPlanets = {},
            navigateToDetail = {}
        )
    }
}
