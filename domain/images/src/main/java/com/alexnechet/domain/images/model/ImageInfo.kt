package com.alexnechet.domain.images.model

data class ImageInfo(
    val basicInfo: BasicImageInfo,
    val largeImageUrl: String,
    val views: String,
    val downloads: String,
    val comments: String,
    val likes: String,
)