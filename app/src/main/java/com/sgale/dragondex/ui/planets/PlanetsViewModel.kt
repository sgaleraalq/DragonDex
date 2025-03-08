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
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.planets.Planet
import com.sgale.dragondex.domain.usecase.FetchPlanets
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val fetchPlanets: FetchPlanets
): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState

    private val planetsFetchingIndex = MutableStateFlow(1)

    private var _isLastItem = MutableStateFlow(false)
    val isLastItem = _isLastItem

    val planetsList: StateFlow<List<Planet>> = planetsFetchingIndex.flatMapLatest { page ->
        fetchPlanets(
            page = page,
            onStart =       { _uiState.value = UIState.Loading      },
            onComplete =    { _uiState.value = UIState.Success      },
            onError =       { _uiState.value = UIState.Error(it)    },
            onLastCall =    { _isLastItem.value = true              }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun fetchNextPlanets() {
        if (_uiState.value != UIState.Loading) {
            planetsFetchingIndex.value++
        }
    }
}