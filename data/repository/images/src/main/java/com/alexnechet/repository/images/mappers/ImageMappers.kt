package com.alexnechet.repository.images.mappers

import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.model.Image
import com.alexnechet.local.images.model.ImageDb
import com.alexnechet.remote.network.model.ImageResponse

fun ImageResponse.toDb() = ImageDb(
    id = id,
    tags = tags,
    previewUrl = previewURL,
    largeImageUrl = largeImageURL,
    views = views,
    comments = comments,
    downloads = downloads,
    collections = collections,
    likes = likes,
    userName = userName,
    userImageUrl = userImageURL
)

fun ImageDb.toImage() = Image(
    id = id,
    tags = tags.orEmpty(),
    previewUrl = previewUrl.orEmpty(),
    largeImageUrl = largeImageUrl.orEmpty(),
    views = views ?: 0,
    comments = comments ?: 0,
    downloads = downloads ?: 0,
    collections = collections ?: 0,
    likes = likes ?: 0,
    userName = userName.orEmpty(),
    userImageUrl = userImageUrl.orEmpty()
)

fun Image.toBasicImageInfo() = BasicImageInfo(
    id = id,
    userName = userName,
    previewUrl = previewUrl,
    userImageUrl = userImageUrl
)
