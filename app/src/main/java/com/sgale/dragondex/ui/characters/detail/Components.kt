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

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.model.characters.CharacterModel
import com.sgale.dragondex.domain.model.characters.Transformation
import com.sgale.dragondex.domain.model.planets.Planet
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.theme.DragonDexTheme
import com.sgale.dragondex.ui.theme.roboto
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun CardDetailInformation(
    modifier: Modifier,
    characterInfo: CharacterModel?
) {
    if (characterInfo == null) return

    var showPlanet by remember { mutableStateOf(false) }
    var showTrans by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(DragonDexTheme.background.color)
            .padding(16.dp)
            .padding(vertical = 8.dp)
    ) {
        CharName(characterInfo.name)
        CharMainInfo(characterInfo)
        Spacer(Modifier.height(32.dp))
        Description(characterInfo.description)
        ExtraInformation(
            onShowPlanet = { showPlanet = true },
            onShowTransformation = { showTrans = true }
        )
    }

    if (showPlanet) {
        DialogOriginPlanet(characterInfo.planet) { showPlanet = false }
    }

    if (showTrans) {
        DialogTransformation(characterInfo.transformations) { showTrans = false }
    }
}

@Composable
fun CharName(
    name: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = name,
        style = saiyanSans.copy(
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = DragonDexTheme.colors.primary,
            letterSpacing = 2.sp
        )
    )
}

@Composable
fun CharMainInfo(
    char: CharacterModel
) {
    val context = LocalContext.current
    Row (
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier.height(250.dp).weight(1f),
            model = ImageRequest.Builder(context).data(char.image).crossfade(true).build(),
            contentDescription = stringResource(R.string.description_character_image),
            contentScale = ContentScale.FillHeight,
            placeholder = painterResource(R.drawable.img_goku)
        )
        Spacer(Modifier.width(24.dp))
        CharacterInformation(char.ki, char.maxKi, char.gender, char.race, char.affiliation)
    }
}

@Composable
fun CharacterInformation(
    ki: String,
    maxKi: String,
    gender: String,
    race: String,
    affiliation: String
) {
    Column {
        CharInformation(stringResource(R.string.base_ki), ki)
        CharInformation(stringResource(R.string.max_ki), maxKi)
        CharInformation(stringResource(R.string.gender), gender)
        CharInformation(stringResource(R.string.info), "$race - $affiliation")
    }
}

@Composable
fun CharInformation(statTitle: String, stat: String) {
    Column {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = statTitle,
            style = roboto.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = DragonDexTheme.colors.primary
            )
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = stat,
            style = roboto.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DragonDexTheme.colors.black
            )
        )
    }
}

@Composable
fun Description(description: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        text = stringResource(R.string.description),
        style = roboto.copy(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DragonDexTheme.colors.primary
        )
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = description,
        style = roboto.copy(
            fontSize = 16.sp,
            color = DragonDexTheme.colors.black,
            textAlign = TextAlign.Justify
        )
    )
}

@Composable
fun ExtraInformation(
    onShowPlanet: () -> Unit,
    onShowTransformation: () -> Unit
) {
    val planetsComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_origin_planet))

    Text(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 22.dp),
        text = stringResource(R.string.extra_information),
        style = roboto.copy(
            fontSize = 16.sp,
            color = DragonDexTheme.colors.primary
        )
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExtraInformationCard(
            modifier = Modifier.weight(1f),
            titleText = stringResource(R.string.origin_planet),
            composition = planetsComposition,
            onItemClicked = { onShowPlanet() }
        )
        Spacer(Modifier.width(12.dp))
        ExtraInformationCard(
            modifier = Modifier.weight(1f),
            titleText = stringResource(R.string.transformations),
            composition = planetsComposition,
            onItemClicked = { onShowTransformation() }
        )
    }
}

@Composable
fun ExtraInformationCard(
    modifier: Modifier,
    titleText: String,
    composition: LottieComposition?,
    onItemClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemCard(
            onItemClicked = { onItemClicked() },
            color = DragonDexTheme.colors.backgroundLight,
            content = {
                LottieAnimation(
                    modifier = Modifier.height(100.dp),
                    composition = composition,
                    iterations = Int.MAX_VALUE
                )
            }
        )
        Text(
            text = titleText,
            style = roboto.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DragonDexTheme.colors.black
            )
        )
    }
}

@Composable
fun DialogOriginPlanet(planet: Planet?, onHideDialog: () -> Unit = {}) {
    if (planet == null) return

    Dialog(
        onDismissRequest = { onHideDialog() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = DragonDexTheme.colors.backgroundLight
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlanetDialogHeader(planet)
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(planet.image)
                        .crossfade(true)
                        .build(),
                    loading = { CircularProgressIndicator() },
                    contentDescription = stringResource(R.string.description_character_image),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    text = planet.description,
                    style = roboto.copy(
                        fontSize = 16.sp,
                        color = DragonDexTheme.colors.black,
                        textAlign = TextAlign.Justify
                    )
                )
            }
        }
    }
}

@Composable
fun PlanetDialogHeader(planet: Planet) {
    val context = LocalContext.current
    val planetDestroyedMsg = stringResource(R.string.planet_destroyed_msg)
    val planetNotDestroyedMsg = stringResource(R.string.planet_not_destroyed_msg)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    Toast.makeText(
                        context,
                        if (planet.isDestroyed) planetDestroyedMsg else planetNotDestroyedMsg,
                        Toast.LENGTH_SHORT
                    ).show()
                },
            painter = painterResource(if (planet.isDestroyed) R.drawable.ic_planet_destroyed else R.drawable.ic_planet),
            contentDescription = stringResource(R.string.description_planet),
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(top = 4.dp),
            text = planet.name.uppercase(),
            style = saiyanSans.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DragonDexTheme.colors.black,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        )
        Icon(
            modifier = Modifier
                .size(24.dp)
                .alpha(0f),
            painter = painterResource(R.drawable.ic_planet),
            contentDescription = stringResource(R.string.description_planet),
            tint = Color.Unspecified
        )
    }
}

@Composable
fun DialogTransformation(transformations: List<Transformation>, onHideDialog: () -> Unit = {}) {
    val noTransformationMsg = stringResource(R.string.no_transformations_found)
    if (transformations.isEmpty()){
        Toast.makeText(LocalContext.current, noTransformationMsg, Toast.LENGTH_SHORT).show()
        onHideDialog()
        return
    }

    Dialog(
        onDismissRequest = { onHideDialog() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = DragonDexTheme.colors.backgroundLight
            )
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                    text = stringResource(R.string.transformations),
                    style = saiyanSans.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = DragonDexTheme.colors.black,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center
                    )
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(transformations.size) {
                        TransformationCard(transformations[it])
                    }
                }
            }

        }
    }
}

@Composable
fun TransformationCard(transformation: Transformation) {
    Box(
        modifier = Modifier.padding(8.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.height(200.dp).align(Alignment.Center),
            model = ImageRequest.Builder(LocalContext.current)
                .data(transformation.image)
                .crossfade(true)
                .build(),
            loading = { CircularProgressIndicator() },
            contentDescription = stringResource(R.string.description_character_image),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(DragonDexTheme.colors.grayTransparent).padding(vertical = 4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = transformation.name,
                style = saiyanSans.copy(
                    color = DragonDexTheme.colors.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = transformation.ki,
                style = roboto.copy(
                    color = DragonDexTheme.colors.black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharacterDetailPreview() {
    DragonDexTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardDetailInformation(
                modifier = Modifier.weight(1f),
                characterInfo = PreviewUtils.mockCharacterInfo()
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharacterOriginPlanetPreview() {
    DragonDexTheme {
        DialogOriginPlanet(
            planet = PreviewUtils.mockPlanet(1)
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CharacterTransformationPreview() {
    DragonDexTheme {
        DialogTransformation(
            transformations = List(5) {
                PreviewUtils.mockTransformation()
            }
        )
    }
}