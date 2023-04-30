package com.alexnechet.remote.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val id: Long,
    val pageUrl: String? = null,
    val tags: String? = null,
    val previewURL: String? = null,
    val previewWidth: Int? = null,
    val previewHeight: Int? = null,
    val webformatUrl: String? = null,
    val webformatWidth: Int? = null,
    val webformatHeight: Int? = null,
    val largeImageURL: String? = null,
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
    val userImageURL: String? = null,
)
