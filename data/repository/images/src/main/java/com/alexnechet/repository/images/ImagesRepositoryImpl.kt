package com.alexnechet.repository.images

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.local.images.RemoteKeysLocalDataSource
import com.alexnechet.remote.datasource.images.ImagesRemoteDataSource
import com.alexnechet.repository.images.mappers.toImage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class ImagesRepositoryImpl(
    private val remote: ImagesRemoteDataSource,
    private val imagesLocalDataSource: ImagesLocalDataSource,
    private val keysLocalDataSource: RemoteKeysLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ImagesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getImages(query: String): Flow<PagingData<Image>> {
        val pageConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            maxSize = PAGE_SIZE * 3
        )

        return Pager(
            config = pageConfig,
            remoteMediator = ImagesRemoteMediator(query, remote, imagesLocalDataSource, keysLocalDataSource)
        ) {
            imagesLocalDataSource.getAllImages()
        }.flow.map { pd ->
            pd.map { it.toImage() }
        }.catch {
            it.printStackTrace()
        }.flowOn(ioDispatcher)
    }


    override fun getImageById(id: Long): Result<Image> {
        TODO("Not yet implemented")
    }

}