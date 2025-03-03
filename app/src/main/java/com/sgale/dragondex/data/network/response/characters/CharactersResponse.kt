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

package com.sgale.dragondex.data.network.response.characters

import com.google.gson.annotations.SerializedName
import com.sgale.dragondex.data.network.response.core.LinksResponse
import com.sgale.dragondex.data.network.response.core.MetaResponse

data class CharactersResponse(
    @SerializedName("items")    val characters: List<CharacterInfoResponse>,
    @SerializedName("meta")     val meta: MetaResponse,
    @SerializedName("links")    val links: LinksResponse
)
