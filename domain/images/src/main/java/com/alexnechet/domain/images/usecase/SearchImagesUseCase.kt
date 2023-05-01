package com.alexnechet.domain.images.usecase

import androidx.paging.PagingData
import com.alexnechet.domain.images.repository.ImagesRepository
import kotlinx.coroutines.flow.catch

class SearchImagesUseCase(private val imagesRepository: ImagesRepository) {
    operator fun invoke(
        query: String,
        onErrorAction: (throwable: Throwable) -> Unit = {}
    ) = imagesRepository.getImages(query)
        .catch {
            it.printStackTrace()
            onErrorAction(it)
            emit(PagingData.empty())
        }
}