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

package com.sgale.dragondex.ui.planets.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.core.BackManagement

@Composable
fun PlanetDetailScreen(
    id: Int,
    navigateBack: () -> Unit,
    viewModel: PlanetDetailViewModel = hiltViewModel()
){
    val context     = LocalContext.current
    val errorMsg    = stringResource(R.string.error_msg)
    val isLoading   by viewModel.isLoading.collectAsStateWithLifecycle()
    val planetInfo  by viewModel.planet.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.fetchPlanet(id)
    }

    LaunchedEffect(isLoading) {
        if (!isLoading && planetInfo == null) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
        }
    }

    Column {
        AsyncImage(
            modifier = Modifier.height(200.dp),
            model = planetInfo?.image,
            contentDescription = null
        )
        Text(
            text = planetInfo?.characters.toString()
        )
    }

    BackManagement(
        navigateBack = navigateBack,
        isLoading = isLoading
    )
}