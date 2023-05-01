package com.alexnechet.feature.images.details

import com.alexnechet.domain.images.model.ImageInfo

sealed class ImageDetailsUIState {
    data class Success(val data: ImageInfo) : ImageDetailsUIState()
    data class Error(val message: String) : ImageDetailsUIState()
    object Loading : ImageDetailsUIState()
}