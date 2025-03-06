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

package com.sgale.dragondex.ui.characters.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sgale.dragondex.domain.FakeRepository
import com.sgale.dragondex.domain.usecase.FetchCharacterById
import com.sgale.dragondex.ui.core.BackManagement
import com.sgale.dragondex.ui.core.PreviewUtils

@Composable
fun CharacterDetailScreen(
    id: Int,
    navigateBack: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val isLoading       by viewModel.isLoading.collectAsStateWithLifecycle()
    val character       by viewModel.character.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.getCharacterById(id)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardDetailInformation( modifier = Modifier.weight(1f), characterInfo = character )
    }

    BackManagement(
        navigateBack = navigateBack,
        isLoading = isLoading
    )
}
