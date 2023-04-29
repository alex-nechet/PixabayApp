package com.alexnechet.domain.images.repository

import com.alexnechet.domain.images.model.Image

interface ImagesRepository {
    fun getImages(query: String, page: Int): Result<List<Image>>
    fun getImageById(id: Long): Result<Image>
}
