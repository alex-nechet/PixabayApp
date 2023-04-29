package com.alexnechet.remote.network.model

import com.squareup.moshi.Json

data class ImageResponse(
    val id: Long,
    val pageUrl: String? = null,
    val type: TypeResponse,
    val tags: String? = null,
    val previewUrl: String? = null,
    val previewWidth: Int? = null,
    val previewHeight: Int? = null,
    val webformatUrl: String? = null,
    val webformatWidth: Int? = null,
    val webformatHeight: Int? = null,
    val largeImageUrl: String? = null,
    @Json(name = "imageWidth")
    val largeImageWidth: Int? = null,
    @Json(name = "imageHeight")
    val largeImageHeight: Int? = null,
    @Json(name = "imageSize")
    val largeImageSize: Int? = null,
    val views: Int? = null,
    val comments: Int? = null,
    val downloads: Int? = null,
    val collections: Int? = null,
    val likes: Int? = null,
    @Json(name = "user")
    val userName: String? = null,
    val userImageUrl: String? = null
)
