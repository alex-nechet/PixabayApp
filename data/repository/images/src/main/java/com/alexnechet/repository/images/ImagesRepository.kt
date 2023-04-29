package com.alexnechet.repository.images

import android.app.DownloadManager.Query
import com.alexnechet.domain.images.model.Image
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.remote.datasource.images.ImagesRemoteDataSource

interface ImagesRepository {
    fun getImages(string: Query, page: Int): Result<List<Image>>

    fun getImageById(id: Long): Result<Image>
}

class ImagesRepositoryImpl(
    private val remote: ImagesRemoteDataSource,
    private val local: ImagesLocalDataSource
) : ImagesRepository {
    override fun getImages(string: Query, page: Int): Result<List<Image>> {
        TODO("Not yet implemented")
    }

    override fun getImageById(id: Long): Result<Image> {
        TODO("Not yet implemented")
    }

}