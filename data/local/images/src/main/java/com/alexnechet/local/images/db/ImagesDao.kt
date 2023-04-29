package com.alexnechet.local.images.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexnechet.local.images.model.ImageDb

@Dao
interface ImagesDao {
    @Query("SELECT * FROM images ORDER BY id")
    fun getImages(): PagingSource<Int, ImageDb>?

    @Query("SELECT * FROM images WHERE id = :id LIMIT 1")
    suspend fun getImage(id: Long): ImageDb?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageDb>)

    @Query("DELETE from images")
    suspend fun deleteAll()
}
