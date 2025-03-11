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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.theme.DragonDexTheme
import com.sgale.dragondex.ui.theme.roboto
import com.sgale.dragondex.ui.theme.saiyanSans
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun BackManagement(
    navigateBack: () -> Unit,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            modifier = Modifier
                .clickable { navigateBack() }
                .size(50.dp)
                .padding(12.dp),
            painter = painterResource(R.drawable.ic_back),
            contentDescription = stringResource(R.string.description_back),
            tint = DragonDexTheme.colors.black
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = DragonDexTheme.colors.primary
            )
        }
    }
}

@Composable
fun DragonDexImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale,
    placeHolder: Int = R.drawable.img_goku
) {
    GlideImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        imageModel = { imageUrl },
        imageOptions = ImageOptions(contentScale = contentScale),
        component = rememberImageComponent {
            +ShimmerPlugin(
                Shimmer.Flash(
                    baseColor = Color.White,
                    highlightColor = Color.LightGray,
                )
            )
        },
        previewPlaceholder = painterResource(placeHolder)
    )
}

@Composable
fun ItemCard(
    content: @Composable () -> Unit,
    id: Int = 0,
    color: Color? = null,
    onItemClicked: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onItemClicked(id)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = color ?: DragonDexTheme.colors.white,
            contentColor = color ?: DragonDexTheme.colors.white,
            disabledContainerColor = color ?: DragonDexTheme.colors.white,
            disabledContentColor = color ?: DragonDexTheme.colors.white,
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        content()
    }
}

@Composable
fun CharacterCardContent(
    name: String,
    image: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            modifier = Modifier.height(150.dp),
            imageModel = { image },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            component = rememberImageComponent {
                +ShimmerPlugin(
                    Shimmer.Flash(
                        baseColor = Color.White,
                        highlightColor = Color.LightGray,
                    )
                )
            },
            previewPlaceholder = painterResource(R.drawable.ic_placeholder)
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
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun PlanetCardContent(
    name: String,
    image: String,
) {
    Box {
        GlideImage(
            modifier = Modifier.height(120.dp),
            imageModel = { image },
            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
            component = rememberImageComponent {
                +ShimmerPlugin(
                    Shimmer.Flash(
                        baseColor = Color.White,
                        highlightColor = Color.LightGray,
                    )
                )
            },
            previewPlaceholder = painterResource(R.drawable.ic_placeholder)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(DragonDexTheme.colors.grayTransparent)
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

@Composable
fun Header(
    text: String,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DragonDexTheme.colors.primary)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable { onBackPressed() }
                .padding(4.dp),
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(R.string.description_back),
            tint = Color.White
        )
        Spacer(Modifier.width(16.dp))
        Icon(
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp),
            painter = painterResource(R.drawable.ic_dragon_ball_logo),
            contentDescription = stringResource(R.string.description_app_icon),
            tint = Color.Unspecified
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = text,
            style = roboto.copy(
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = Bold
            )
        )
    }
}
