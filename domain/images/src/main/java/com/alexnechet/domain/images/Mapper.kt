package com.alexnechet.domain.images

import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.model.Image

fun Image.toBasicImageInfo() = BasicImageInfo(
    id = id,
    userName = userName,
    previewUrl = previewUrl,
    userImageUrl = userImageUrl,
    dbId = dbId,
)