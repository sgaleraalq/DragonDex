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

package com.sgale.dragondex.ui.launch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sgale.dragondex.R

@Composable
fun LaunchScreen(
    navigateToCharacters: () -> Unit = {},
    navigateToPlanets: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Just the title"
        )

        Column(
            modifier = Modifier.weight(1f).fillMaxWidth()
        ) {
            LaunchCard(
                text = stringResource(R.string.characters),
                onLaunchCardPressed = { navigateToCharacters() }
            )
            Spacer(Modifier.height(12.dp))
            LaunchCard(
                text = stringResource(R.string.planets),
                onLaunchCardPressed = { navigateToPlanets() }
            )
        }
    }
}