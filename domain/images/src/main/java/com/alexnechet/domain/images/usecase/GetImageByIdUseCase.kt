package com.alexnechet.domain.images.usecase

import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.repository.ImagesRepository

class GetImageByIdUseCase(private val imagesRepository: ImagesRepository) {
    suspend operator fun invoke(
        imageId: Long,
        onErrorAction: (throwable: Throwable) -> Unit = {}
    ): Image? = imagesRepository.getImageById(imageId).fold(
        onSuccess = { it },
        onFailure = {
            onErrorAction(it)
            null
        }
    )
}