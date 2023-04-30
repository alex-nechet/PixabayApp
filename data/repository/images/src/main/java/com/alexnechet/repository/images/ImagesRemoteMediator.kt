package com.alexnechet.repository.images

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.local.images.RemoteKeysLocalDataSource
import com.alexnechet.local.images.model.ImageDb
import com.alexnechet.local.images.model.RemoteKeys
import com.alexnechet.remote.datasource.images.ImagesRemoteDataSource
import com.alexnechet.repository.images.mappers.toDb


private const val START_PAGE_INDEX = 1

@ExperimentalPagingApi
internal class ImagesRemoteMediator(
    private val query: String,
    private val remote: ImagesRemoteDataSource,
    private val imagesLocal: ImagesLocalDataSource,
    private val imageKeysLocal: RemoteKeysLocalDataSource
) : RemoteMediator<Int, ImageDb>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDb>
    ): MediatorResult {


        val page = when (loadType) {
            LoadType.REFRESH -> {
                START_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        return try {
            val apiResponse = remote(query = query, page = page, size = state.config.pageSize)

            val images = apiResponse.imageList.map { it.toDb() }
            val endOfPaginationReached = images.isEmpty()


            if (loadType == LoadType.REFRESH) {
                imageKeysLocal.clearRemoteKeys()
                imagesLocal.deleteAll()
            }

            val prevKey = if (page == START_PAGE_INDEX) null else page - 1
             val nextKey = if (endOfPaginationReached) null else page + 1

            val keys = images.map {
                RemoteKeys(id = it.id, prevKey = prevKey, nextKey = nextKey)
            }

            imageKeysLocal.insertAll(keys)
            imagesLocal.insertAll(images)

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ImageDb>): RemoteKeys? {
        val lastItem = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        return lastItem?.let { image -> imageKeysLocal.remoteKeysImageId(image.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ImageDb>): RemoteKeys? {
        val firstItem = state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
        return firstItem?.let { image -> imageKeysLocal.remoteKeysImageId(image.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ImageDb>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { imageId ->
                imageKeysLocal.remoteKeysImageId(imageId)
            }
        }
    }
}

