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

package com.sgale.dragondex.ui.planets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgale.dragondex.domain.model.planets.PlanetsListModel
import com.sgale.dragondex.domain.usecase.GetAllPlanets
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val getAllPlanets: GetAllPlanets
): ViewModel() {

    private val _planetsList = MutableStateFlow<PlanetsListModel?>(null)
    val planetsList = _planetsList

    init {
        viewModelScope.launch {
            val planetsList = withContext(Dispatchers.IO) {
                getAllPlanets()
            }
            if (planetsList != null) {
                _planetsList.value = planetsList
            }
        }
    }
}