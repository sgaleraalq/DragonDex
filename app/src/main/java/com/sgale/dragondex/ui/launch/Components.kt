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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.theme.saiyanSans

@Composable
fun Title(
    text: String
) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        text = text,
        style = saiyanSans.copy(
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    )
}

@Composable
fun LaunchCard(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes image: Int,
    onLaunchCardPressed: () -> Unit
) {
    Card(
        modifier = modifier.padding(16.dp).clickable { onLaunchCardPressed() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box{
            Image(
                modifier = Modifier.fillMaxWidth().height(300.dp),
                painter = painterResource(image),
                contentDescription = stringResource(R.string.description_planet),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                text = text,
                style = saiyanSans.copy(
                    fontSize = 18.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}