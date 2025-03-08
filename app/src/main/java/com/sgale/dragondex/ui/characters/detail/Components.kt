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

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
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
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.domain.model.characters.OriginPlanet
import com.sgale.dragondex.domain.model.characters.Transformation
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PreviewUtils
import com.sgale.dragondex.ui.theme.grayTransparent
import com.sgale.dragondex.ui.theme.primary
import com.sgale.dragondex.ui.theme.primaryDark
import com.sgale.dragondex.ui.theme.roboto
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun CardDetailInformation(
    modifier: Modifier,
    characterInfo: CharacterInfo?
) {
    if (characterInfo == null) return

    var showPlanet by remember { mutableStateOf(false) }
    var showTrans by remember { mutableStateOf(false) }

    CharImage(characterInfo.image, characterInfo.affiliation)
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(primaryDark)
            .padding(16.dp)
            .padding(vertical = 8.dp)
    ) {
        CharName(characterInfo.name)
        Spacer(Modifier.height(8.dp))
        CharacterInformation(
            characterInfo.gender,
            characterInfo.race.name,
            characterInfo.ki,
            characterInfo.maxKi
        )
        Spacer(Modifier.height(32.dp))
        Description(characterInfo.description)
        ExtraInformation(
            onShowPlanet = { showPlanet = true },
            onShowTransformation = { showTrans = true }
        )
    }

    if (showPlanet) {
        DialogOriginPlanet(characterInfo.originPlanet) { showPlanet = false }
    }

    if (showTrans) {
        DialogTransformation(characterInfo.transformations) { showTrans = false }
    }
}

@Composable
fun CharImage(image: String, affiliation: String) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .height(400.dp)
            .padding(vertical = 16.dp)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(context).data(image).crossfade(true).build(),
            contentDescription = stringResource(R.string.description_character_image),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_placeholder)
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            text = affiliation,
            style = roboto.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                textAlign = TextAlign.Center
            )
        )
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
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = primary,
            letterSpacing = 2.sp
        )
    )
}

@Composable
fun CharacterInformation(
    gender: String,
    race: String,
    ki: String,
    maxKi: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        CharInformation(stringResource(R.string.info), "$race\n$gender")
        CharInformation(stringResource(R.string.base_ki), ki)
        CharInformation(stringResource(R.string.max_ki), maxKi)
    }
}

@Composable
fun CharInformation(statTitle: String, stat: String) {
    Column {
        Text(
            text = statTitle,
            style = roboto.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = primary
            )
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = stat,
            style = roboto.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White
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
            color = primary
        )
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = description,
        style = roboto.copy(
            fontSize = 16.sp,
            color = White,
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
            color = primary
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
                color = White
            )
        )
    }
}

@Composable
fun DialogOriginPlanet(originPlanet: OriginPlanet?, onHideDialog: () -> Unit = {}) {
    if (originPlanet == null) return

    Dialog(
        onDismissRequest = { onHideDialog() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlanetDialogHeader(originPlanet)
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(originPlanet.image)
                        .crossfade(true)
                        .build(),
                    loading = { CircularProgressIndicator() },
                    contentDescription = stringResource(R.string.description_character_image),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    text = originPlanet.description,
                    style = roboto.copy(
                        fontSize = 16.sp,
                        color = Black,
                        textAlign = TextAlign.Justify
                    )
                )
            }
        }
    }
}

@Composable
fun PlanetDialogHeader(originPlanet: OriginPlanet) {
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
                        if (originPlanet.isDestroyed) planetDestroyedMsg else planetNotDestroyedMsg,
                        Toast.LENGTH_SHORT
                    ).show()
                },
            painter = painterResource(if (originPlanet.isDestroyed) R.drawable.ic_planet_destroyed else R.drawable.ic_planet),
            contentDescription = stringResource(R.string.description_planet),
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(top = 4.dp),
            text = originPlanet.name.uppercase(),
            style = saiyanSans.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
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
    val noTransformationMsg = stringResource(R.string.error_msg)
    if (transformations.isEmpty()){
        Toast.makeText(LocalContext.current, noTransformationMsg, Toast.LENGTH_SHORT).show()
        onHideDialog()
        return
    }

    Dialog(
        onDismissRequest = { onHideDialog() }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    text = stringResource(R.string.transformations),
                    style = saiyanSans.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black,
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
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(grayTransparent).padding(vertical = 4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = transformation.name,
                style = saiyanSans.copy(
                    color = primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = transformation.ki,
                style = roboto.copy(
                    color = White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

@Preview
@Composable
private fun CharacterDetailPreview() {
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

@Preview
@Composable
private fun CharacterOriginPlanetPreview() {
    DialogOriginPlanet(
        originPlanet = PreviewUtils.mockOriginPlanet()
    )
}

@Preview
@Composable
private fun CharacterTransformationPreview() {
    DialogTransformation(
        transformations = List(5) {
            PreviewUtils.mockTransformation()
        }
    )
}