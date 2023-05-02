package com.alexnechet.local.images

import androidx.paging.PagingSource
import com.alexnechet.local.images.db.ImagesDao
import com.alexnechet.local.images.db.ImagesDatabase
import com.alexnechet.local.images.model.ImageDb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ImagesLocalDataSource {
    fun getAllImages(): PagingSource<Int, ImageDb>

    suspend fun insertAll(images: List<ImageDb>)
    suspend fun deleteAll()
    suspend fun getImage(id: Long): ImageDb?
}

class ImagesLocalDataSourceImpl(
    private val dao: ImagesDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ImagesLocalDataSource {
    override fun getAllImages(): PagingSource<Int, ImageDb> = dao.getImages()

    override suspend fun getImage(id: Long): ImageDb? = withContext(dispatcher) { dao.getImage(id) }

    override suspend fun insertAll(images: List<ImageDb>) = withContext(dispatcher) {
        dao.insertAll(images)
    }

    override suspend fun deleteAll() = withContext(dispatcher) {
        dao.deleteAll()
    }
}