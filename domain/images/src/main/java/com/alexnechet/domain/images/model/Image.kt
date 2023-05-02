package com.alexnechet.domain.images.model

data class Image(
    val id: Long,
    val tags: String,
    val previewUrl: String,
    val largeImageUrl: String,
    val views: Int,
    val comments: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val userName: String,
    val userImageUrl: String
)