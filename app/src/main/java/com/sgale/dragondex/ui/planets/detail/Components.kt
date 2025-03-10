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

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.model.Planet
import com.sgale.dragondex.ui.characters.detail.Description
import com.sgale.dragondex.ui.core.DragonDexImage
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.theme.DragonDexTheme
import com.sgale.dragondex.ui.theme.roboto
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun PlanetDetailInformation(planet: Planet?) {
    if (planet == null) return

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .background(DragonDexTheme.background.color)
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        item(
            span = { GridItemSpan(3) }
        ) {
            Column {
                PlanetTitle(planet.name)
                Spacer(Modifier.height(12.dp))
                DragonDexImage(
                    modifier = Modifier.fillMaxWidth(),
                    imageUrl = planet.image,
                    description = stringResource(R.string.description_planet_image),
                    contentScale = ContentScale.Inside,
                    placeHolder = R.drawable.img_planet_namek
                )
                PlanetDestroyed(planet.isDestroyed)
                Description(planet.description)
                Spacer(Modifier.height(24.dp))
                CharsTitle()
                Spacer(Modifier.height(24.dp))
            }
        }
        if (planet.characters.isEmpty()) {
            item(span = { GridItemSpan(3) }) {
                NoCharsText()
            }
        } else {
            itemsIndexed(
                items = planet.characters,
                key = { _, char -> char.name }
            ) { _, char ->
                PlanetCharCard(
                    name = char.name,
                    image = char.image
                )
            }
        }
    }
}

@Composable
fun PlanetTitle(planetName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = planetName,
        style = saiyanSans.copy(
            color = DragonDexTheme.colors.primary,
            fontSize = 34.sp,
            letterSpacing = 1.5.sp,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun PlanetDestroyed(isDestroyed: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(if (isDestroyed) R.drawable.ic_planet_destroyed else R.drawable.ic_planet),
            contentDescription = stringResource(R.string.description_planet_image),
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(32.dp))
        Text(
            text = stringResource(if (isDestroyed) R.string.planet_destroyed else R.string.planet_alive).uppercase(),
            style = roboto.copy(
                color = DragonDexTheme.colors.black,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun CharsTitle() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        text = stringResource(R.string.characters),
        style = roboto.copy(
            color = DragonDexTheme.colors.primary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun NoCharsText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        text = stringResource(R.string.no_characters),
        style = roboto.copy(
            color = DragonDexTheme.colors.black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun PlanetCharCard(name: String, image: String) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DragonDexImage(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            imageUrl = image,
            description = stringResource(R.string.description_character_image),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = name,
            style = roboto.copy(
                color = DragonDexTheme.colors.black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PlanetDetailScreenPreview() {
    DragonDexTheme {
        PlanetDetailInformation(
            planet = PreviewUtils.mockPlanet(0)
        )
    }
}