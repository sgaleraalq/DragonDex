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

package com.sgale.dragondex.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.theme.grayTransparent
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun ItemCard(
    content: @Composable () -> Unit,
    id: Int,
    onItemClicked: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClicked(id)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        content()
    }
}

@Composable
fun CharacterContent(
    name: String,
    image: String,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = stringResource(R.string.description_character_image),
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .background(Color.Transparent),
            text = name,
            style = saiyanSans.copy(
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun PlanetContent(
    name: String,
    image: String,
) {
    val context = LocalContext.current
    Box{
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_placeholder),
            contentDescription = stringResource(R.string.description_character_image),
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(grayTransparent)
                .align(Alignment.BottomCenter)
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = name,
            style = saiyanSans.copy(
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}