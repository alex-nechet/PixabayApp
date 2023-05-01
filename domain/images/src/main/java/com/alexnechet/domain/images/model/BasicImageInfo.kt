package com.alexnechet.domain.images.model

data class BasicImageInfo(
    val id: Long,
    val userName: String,
    val previewUrl: String,
    val userImageUrl: String,
    val tags: String,
)

