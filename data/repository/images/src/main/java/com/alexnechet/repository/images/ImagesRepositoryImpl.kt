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

private const val PAGE_SIZE = 20

class ImagesRepositoryImpl(
    private val remote: ImagesRemoteDataSource,
    private val imagesLocalDataSource: ImagesLocalDataSource,
    private val keysLocalDataSource: RemoteKeysLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ImagesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getImages(query: String): Flow<PagingData<Image>> {
        val sourceFactory = {
            imagesLocalDataSource.getAllImages()
                ?: throw IllegalStateException("Database is not initialized")
        }

        val pageConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        )

        val remoteMediator = ImagesRemoteMediator(
            query = query,
            remote = remote,
            imagesLocal = imagesLocalDataSource,
            imageKeysLocal = keysLocalDataSource
        )

        return Pager(
            config = pageConfig,
            remoteMediator = remoteMediator,
            pagingSourceFactory = sourceFactory
        ).flow.map { pd ->
            pd.map { it.toImage() }
        }.catch {
            it.printStackTrace()
        }.flowOn(ioDispatcher)
    }


    override fun getImageById(id: Long): Result<Image> {
        TODO("Not yet implemented")
    }

}