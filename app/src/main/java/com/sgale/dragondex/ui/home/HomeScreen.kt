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

package com.sgale.dragondex.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.background.Background
import com.sgale.dragondex.ui.theme.DragonDexTheme

@Composable
fun HomeScreen(
    navigateToCharacters: () -> Unit = {},
    navigateToPlanets: () -> Unit = {}
) {
    Background()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.height(32.dp))
        TitleLogo()
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LaunchCard(
                text = stringResource(R.string.characters),
                image = R.drawable.img_dragon_ball_characters,
                onLaunchCardPressed = { navigateToCharacters() }
            )
            LaunchCard(
                text = stringResource(R.string.planets),
                image = R.drawable.img_king_kais_planet,
                onLaunchCardPressed = { navigateToPlanets() }
            )
        }
        Spacer(Modifier.height(300.dp))
    }

}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    DragonDexTheme {
        HomeScreen()
    }
}