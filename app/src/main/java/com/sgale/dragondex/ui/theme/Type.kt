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

package com.sgale.dragondex.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sgale.dragondex.R

val saiyanSans = TextStyle(
    fontFamily = FontFamily(
        Font(R.font.saiyan_sans, FontWeight.Normal),
    ),
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.5.sp
)

val roboto = TextStyle(
    fontFamily =  FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold,    FontWeight.Bold),
        Font(R.font.roboto_italic,  FontWeight.Normal, FontStyle.Italic)
    ),
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
)