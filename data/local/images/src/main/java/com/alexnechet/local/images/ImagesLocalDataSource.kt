package com.alexnechet.local.images

import androidx.paging.PagingSource
import com.alexnechet.local.images.db.ImagesDao
import com.alexnechet.local.images.model.ImageDb

interface ImagesLocalDataSource {
    fun getImages(): PagingSource<Int, ImageDb>?
    suspend fun insertAll(images: List<ImageDb>)
    suspend fun deleteAll()
    suspend fun getImage(id: Long): ImageDb?
}

class ImagesLocalDataSourceImpl(private val dao: ImagesDao) : ImagesLocalDataSource {

    override fun getImages(): PagingSource<Int, ImageDb>? = dao.getImages()

    override suspend fun getImage(id: Long): ImageDb? = dao.getImage(id)

    override suspend fun insertAll(images: List<ImageDb>) = dao.insertAll(images)

    override suspend fun deleteAll() = dao.deleteAll()
}