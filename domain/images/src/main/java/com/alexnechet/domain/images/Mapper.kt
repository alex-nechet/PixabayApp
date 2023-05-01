package com.alexnechet.domain.images

import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.model.ImageInfo

fun Image.toBasicImageInfo() = BasicImageInfo(
    id = id,
    userName = userName,
    previewUrl = previewUrl,
    userImageUrl = userImageUrl,
    tags = tags
)

fun Image.toImageInfo() = ImageInfo(
    basicInfo = this.toBasicImageInfo(),
    largeImageUrl = largeImageUrl,
    likes = likes.toString(),
    downloads = downloads.toString(),
    comments = comments.toString()
)