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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.ui.core.ItemCard
import com.sgale.dragondex.ui.core.PreviewUtils
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
        Spacer(Modifier.height(22.dp))
        Description(characterInfo.description)
        ExtraInformation()
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
fun ExtraInformation() {
    val planetsComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_origin_planet))

    Text(
        modifier = Modifier.padding(horizontal = 8.dp).padding(top = 22.dp),
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
        ExtraInformationCard (
            modifier = Modifier.weight(1f),
            titleText = stringResource(R.string.origin_planet),
            composition = planetsComposition
        )
        Spacer(Modifier.width(12.dp))
        ExtraInformationCard (
            modifier = Modifier.weight(1f),
            titleText = stringResource(R.string.transformations),
            composition = planetsComposition
        )
    }
}


@Composable
fun ExtraInformationCard(
    modifier: Modifier,
    titleText: String,
    composition: LottieComposition?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemCard(
            onItemClicked = { },
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