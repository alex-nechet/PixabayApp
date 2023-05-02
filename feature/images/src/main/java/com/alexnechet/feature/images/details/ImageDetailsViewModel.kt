package com.alexnechet.feature.images.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexnechet.domain.images.toImageInfo
import com.alexnechet.domain.images.usecase.GetImageByIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ImageDetailsViewModel(
    imageId: Long,
    private val getImageByIdUseCase: GetImageByIdUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow<ImageDetailsUIState>(ImageDetailsUIState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            _state.value = ImageDetailsUIState.Loading
            val image = getImageByIdUseCase(imageId) { throwable ->
                _state.value = ImageDetailsUIState.Error(throwable.localizedMessage.orEmpty())
            }

            image?.let { _state.value = ImageDetailsUIState.Success(it.toImageInfo()) }
        }
    }
}