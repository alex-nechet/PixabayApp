package com.alexnechet.feature.images.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.alexnechet.domain.images.toBasicImageInfo
import com.alexnechet.domain.images.usecase.SearchImagesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private const val DEBOUNCE_VALUE = 600L

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class ImageListViewModel(
    initialSearchWord: String,
    private val searchImagesUseCase: SearchImagesUseCase
) : ViewModel() {
    private val searchText = MutableStateFlow(initialSearchWord)
    val data = searchText.debounce(DEBOUNCE_VALUE)
        .flatMapLatest { text ->
            searchImagesUseCase(text).map { pagingData ->
                pagingData.map { it.toBasicImageInfo() }
            }
        }
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = PagingData.empty()
        )

    fun search(searchText: String) {
        this.searchText.value = searchText
    }
}