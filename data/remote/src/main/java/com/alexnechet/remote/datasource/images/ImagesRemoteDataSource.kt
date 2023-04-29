package com.alexnechet.remote.datasource.images

import com.alexnechet.remote.network.PixabayApi
import com.alexnechet.remote.network.model.ResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface ImagesRemoteDataSource {
    suspend operator fun invoke(query: String?, page: Int): ResponseData
}

class ImagesRemoteDataSourceImpl(
    private val api: PixabayApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ImagesRemoteDataSource {
    override suspend operator fun invoke(query: String?, page: Int): ResponseData =
        withContext(ioDispatcher) {
            api.getImages(query, page)
        }
}