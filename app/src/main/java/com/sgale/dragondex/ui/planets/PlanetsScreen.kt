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

package com.sgale.dragondex.ui.planets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.core.Header
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PlanetCardContent

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel = hiltViewModel(),
    navigateHome: () -> Unit
) {
    val planetsList by viewModel.planetsList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            text = stringResource(R.string.planets),
            onBackPressed = { navigateHome() }
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(planetsList.size) { index ->
                val planet = planetsList[index]
                ItemCard(
                    id = planet.id,
                    content = {
                        PlanetCardContent(
                            name = planet.name,
                            image = planet.image
                        )
                    },
                    onItemClicked = { }
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PlanetsMainPreview() {
    PlanetsScreen(
        navigateHome = {}
    )
}
