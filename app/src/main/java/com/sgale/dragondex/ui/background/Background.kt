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

package com.sgale.dragondex.ui.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sgale.dragondex.R
import com.sgale.dragondex.ui.theme.DragonDexTheme

@Composable
fun Background(){
    val waterLottie by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.main_background)
    )
    Box(
        modifier = Modifier.fillMaxSize().background(
            brush = Brush.radialGradient(
                colors = listOf(
                    DragonDexTheme.colors.bluePrimary,
                    DragonDexTheme.colors.blueSecondary
                ),
                center = Offset(x = 0.5f, y = 1f),
                radius = 2500f
            )
        )
    ){
        LottieAnimation(
            modifier = Modifier.fillMaxWidth().height(200.dp).align(Alignment.BottomCenter),
            composition = waterLottie,
            iterations = Int.MAX_VALUE,
            contentScale = ContentScale.Crop
        )
    }
}