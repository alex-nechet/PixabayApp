package com.alexnechet.domain.images.usecase

import androidx.paging.map
import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.domain.images.toBasicImageInfo
import kotlinx.coroutines.flow.map

class GetBasicImagesInfoUseCase(private val imagesRepository: ImagesRepository) {
    operator fun invoke(query: String) = imagesRepository.getImages(query).map { pagingData ->
        pagingData.map { it.toBasicImageInfo() }
    }
}