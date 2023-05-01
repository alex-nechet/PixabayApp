package com.alexnechet.domain.images.model

data class ImageInfo(
    val basicInfo: BasicImageInfo,
    val largeImageUrl: String,
    val downloads: String,
    val comments: String,
    val likes: String,
)