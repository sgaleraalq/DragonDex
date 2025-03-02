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

package com.sgale.dragondex.ui.core

import android.util.Log
import androidx.lifecycle.ViewModel

class CharacterPaginationManager(
    private val viewModel : ViewModel
) {
    private var isLoading = false
    private var currentPage = 0
    private val threshold = 8

    fun onScrollReachedEnd(totalItemCount: Int, visibleItemCount: Int, firstVisibleItemPosition: Int) {
        // Si ya estamos cargando o si hemos alcanzado el final, no hacer nada
        Log.i("Pagination", "onScrollReachedEnd")
        if (isLoading || totalItemCount == 0 || firstVisibleItemPosition + visibleItemCount + threshold < totalItemCount) {
            return
        }

        // Cargar más elementos
        loadMoreData()
    }

    private fun loadMoreData() {
        Log.i("Pagination", "loadMoreData")
        isLoading = true
        currentPage++
//        viewModel.fetchNextCharacterList(currentPage) {
//            isLoading = false
//        }
    }
}