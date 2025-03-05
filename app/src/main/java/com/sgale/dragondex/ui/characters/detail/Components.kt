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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sgale.dragondex.R
import com.sgale.dragondex.domain.model.characters.CharacterInfo
import com.sgale.dragondex.ui.theme.primary
import com.sgale.dragondex.ui.theme.roboto
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun CardDetailInformation(
    characterInfo: CharacterInfo?
) {
    if (characterInfo == null) return

    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest
            .Builder(context)
            .data(characterInfo.image)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(R.string.description_character_image),
        modifier = Modifier.height(400.dp),
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_placeholder)
    )
    Spacer(Modifier.height(8.dp))
    Text(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        text = characterInfo.name,
        style = saiyanSans.copy(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = primary
        )
    )
    Spacer(Modifier.height(8.dp))
    Text(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        text = characterInfo.description,
        style = roboto.copy(
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Justify
        )
    )
}