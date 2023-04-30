package com.alexnechet.domain.images.model

data class BasicImageInfo(
    val dbId: Long,
    val id: Long,
    val userName: String,
    val previewUrl: String,
    val userImageUrl: String
)

