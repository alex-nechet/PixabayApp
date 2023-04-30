package com.alexnechet.feature.images.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.usecase.GetBasicImagesInfoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


sealed class ImageListState {
    data class RequestState(val request: String) : ImageListState()
    data class ResponseState(val data: PagingData<BasicImageInfo>) : ImageListState()
}

class ImageListViewModel(
    private val getBasicImagesInfoUseCase: GetBasicImagesInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ImageListState>(ImageListState.RequestState("fruits"))
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ImageListState.RequestState("fruits")
    )

    fun getImages(query: String) {
        viewModelScope.launch {
             getBasicImagesInfoUseCase(query).cachedIn(viewModelScope).collectLatest {
                 _state.value = ImageListState.ResponseState(it)
             }
        }
    }

    fun search(searchText: String) {
        _state.value = ImageListState.RequestState(searchText)
    }

}