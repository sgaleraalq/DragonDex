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

package com.sgale.dragondex.ui.characters.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgale.dragondex.data.provider.AffiliationsProvider
import com.sgale.dragondex.data.provider.RacesProvider
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.AffiliationsModel
import com.sgale.dragondex.domain.model.CharacterModel
import com.sgale.dragondex.domain.usecase.FetchCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    racesProvider: RacesProvider,
    affiliationsProvider: AffiliationsProvider,
    private val fetchCharacters: FetchCharacters
): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState

    private val _racesList = MutableStateFlow<List<String>>(emptyList())
    val racesList = _racesList

    private val _affiliationsList = MutableStateFlow<List<String>>(emptyList())
    val affiliationsList = _affiliationsList

    init {
        _racesList.value = racesProvider.provideRaces()
        _affiliationsList.value = affiliationsProvider.provideAffiliations()
    }

    val selectedRace = MutableStateFlow<String?>(null)
    val selectedAffiliation = MutableStateFlow<String?>(null)

    fun changeRace(race: String) { selectedRace.value = race }
    fun changeAffiliation(affiliation: String) { selectedAffiliation.value = affiliation }

    private val charactersFetchingIndex = MutableStateFlow(1)

    private var _isLastItem = MutableStateFlow(false)
    val isLastItem = _isLastItem

    val characterList: StateFlow<List<CharacterModel>> = combine(
        charactersFetchingIndex,
        selectedRace,
        selectedAffiliation
    ) { page, race, affiliation ->
        Triple(page, race, affiliation) // Empaquetamos los valores en un Triple
    }.flatMapLatest { (page, race, affiliation) ->
        fetchCharacters(
            page = page,
            race = race,
            affiliation = affiliation, // <-- Agregamos el filtro por afiliación
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

    fun fetchNextCharacters() {
        if (_uiState.value != UIState.Loading) {
            charactersFetchingIndex.value++
        }
    }
}
