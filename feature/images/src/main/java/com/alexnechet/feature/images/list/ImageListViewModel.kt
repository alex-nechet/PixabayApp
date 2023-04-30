package com.alexnechet.feature.images.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.usecase.GetBasicImagesInfoUseCase
import com.alexnechet.feature.common.debounce
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch


class ImageListViewModel(
    private val getBasicImagesInfoUseCase: GetBasicImagesInfoUseCase
) : ViewModel() {

    private val searchText = MutableStateFlow("")
    val data = searchText.asStateFlow().flatMapLatest { text ->
            getBasicImagesInfoUseCase(text)
        }.cachedIn(viewModelScope)

    fun search(searchText: String) {
        this.searchText.value = searchText
    }
}