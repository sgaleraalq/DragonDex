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
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.characters.CharacterListModel
import com.sgale.dragondex.domain.usecase.FetchCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharacters: FetchCharacters
): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState

    private val _toastMsg = MutableStateFlow<String?>(null)
    val toastMsg = _toastMsg

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    private val _characters = MutableStateFlow(CharacterListModel(emptyList()))
    val characters = _characters

    private val charactersFetchingIndex = MutableStateFlow(0)

    private suspend fun fetch() = fetchCharacters(
        page    = charactersFetchingIndex.value,
        onStart = { _isLoading.value = true },
        onComplete = { _isLoading.value = false },
        onError = { _toastMsg.value = it }
    )

    init {
        viewModelScope.launch {
            _isLoading.value = true
            val charactersList = withContext(Dispatchers.IO){ fetch() }

            if (charactersList != null) {
                _characters.value = charactersList
            }
            _isLoading.value = false
        }
    }

    /**
     * When not loading, fetch next characters until reaching the end
     */
    fun fetchNextCharacters() {
        if (!_isLoading.value) {
            charactersFetchingIndex.value++
            Log.i("sgalera", "fetchNextCharacters: ${charactersFetchingIndex.value}")
            viewModelScope.launch {
                val charactersList = withContext(Dispatchers.IO) { fetch() }

                if (charactersList != null) {
                    _characters.value = _characters.value.copy(
                        items = _characters.value.items + charactersList.items
                    )
                }
            }
        }
    }
}
