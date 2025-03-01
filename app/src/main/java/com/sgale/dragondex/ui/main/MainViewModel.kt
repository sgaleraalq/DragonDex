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

package com.sgale.dragondex.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgale.dragondex.domain.core.UIState
import com.sgale.dragondex.domain.model.characters.CharacterListModel
import com.sgale.dragondex.domain.usecase.GetAllCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCharacters: GetAllCharacters
): ViewModel() {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState

    private val _characters = MutableStateFlow<CharacterListModel?>(null)
    val characters = _characters

    init {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            val charactersList = withContext(Dispatchers.IO){
                getAllCharacters()
            }

            if (charactersList != null) {
                _characters.value = charactersList
                _uiState.value = UIState.Success(charactersList)
            } else {
                _uiState.value = UIState.Error("Ha ocurrido un error")
            }
        }
    }
}
