package com.alexnechet.repository.images

import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.remote.datasource.images.ImagesRemoteDataSource


class ImagesRepositoryImpl(
    private val remote: ImagesRemoteDataSource,
    private val local: ImagesLocalDataSource
) : ImagesRepository {
    override fun getImages(query: String, page: Int): Result<List<Image>> {
        TODO("Not yet implemented")
    }

    override fun getImageById(id: Long): Result<Image> {
        TODO("Not yet implemented")
    }

}