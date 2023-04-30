package com.alexnechet.local.images.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageDb(
    @PrimaryKey(autoGenerate = true)
    val databaseId : Long = 0,
    val id: Long,
    val tags: String? = null,
    val previewUrl: String? = null,
    val largeImageUrl: String? = null,
    val views: Int? = null,
    val comments: Int? = null,
    val downloads: Int? = null,
    val collections: Int? = null,
    val likes: Int? = null,
    val userName: String? = null,
    val userImageUrl: String? = null
)