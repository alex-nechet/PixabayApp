package com.alexnechet.domain.images.repository

import androidx.paging.PagingData
import com.alexnechet.domain.images.model.Image
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImages(query: String): Flow<PagingData<Image>>

    suspend fun getImageById(id: Long): Result<Image>
}
