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

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgale.dragondex.data.provider.AffiliationsProvider
import com.sgale.dragondex.data.provider.RacesProvider
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.CharacterModel
import com.sgale.dragondex.domain.usecase.FetchCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    racesProvider: RacesProvider,
    affiliationsProvider: AffiliationsProvider,
    private val fetchCharacters: FetchCharacters
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState

    private val _racesList = MutableStateFlow<List<String>>(emptyList())
    val racesList = _racesList

    private val _affiliationsList = MutableStateFlow<List<String>>(emptyList())
    val affiliationsList = _affiliationsList

    private val _isLastItem = MutableStateFlow(false)
    val isLastItem = _isLastItem

    private val charactersFetchingIndex = MutableStateFlow(1)

    val selectedRace = MutableStateFlow<String?>(null)
    val selectedAffiliation = MutableStateFlow<String?>(null)

    private val _characterList = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characterList: StateFlow<List<CharacterModel>> = _characterList

    init {
        _racesList.value = racesProvider.provideRaces()
        _affiliationsList.value = affiliationsProvider.provideAffiliations()
        fetchCharactersList()
    }

    private fun fetchCharactersList() {
        viewModelScope.launch {
            fetchCharacters(
                page = charactersFetchingIndex.value,
                onStart = { _uiState.value = UIState.Loading },
                onComplete = { _uiState.value = UIState.Success },
                onError = { _uiState.value = UIState.Error(it) },
                onLastCall = { _isLastItem.value = true }
            ).collect { newCharacters ->
                _characterList.value = newCharacters
                applyFilters()
            }
        }
    }

    private fun applyFilters() {
        _characterList.value = _characterList.value.map { character ->
            character.copy(
                isVisible = (selectedRace.value == null || character.race == selectedRace.value) &&
                        (selectedAffiliation.value == null || character.affiliation == selectedAffiliation.value)
            )
        }.toList()
        Log.i("CharactersViewModel", "applyFilters: ${_characterList.value}")
    }

    fun changeRace(race: String?) {
        selectedRace.value = race
        applyFilters()
    }

    fun changeAffiliation(affiliation: String?) {
        selectedAffiliation.value = affiliation
        applyFilters()
    }

    fun fetchNextCharacters() {
        if (_uiState.value != UIState.Loading) {
            charactersFetchingIndex.value++
            fetchCharactersList()
        }
    }
}
