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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    onLaunchCardPressed: () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp).clickable { onLaunchCardPressed() }
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = text,
                style = saiyanSans.copy(
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
    }
}