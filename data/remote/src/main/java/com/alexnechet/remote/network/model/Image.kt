package com.alexnechet.remote.network.model

import com.squareup.moshi.Json

data class Image(
    val id: Long,
    val pageUrl: String? = null,
    val type: Type,
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
    val largeImageSize: Long? = null,
    val views: Long? = null,
    val downloads: Long? = null,
    val collections: Int? = null,
    val likes: Long? = null,
    @Json(name = "user")
    val userName: String? = null,
    val userImageUrl: String? = null
)
