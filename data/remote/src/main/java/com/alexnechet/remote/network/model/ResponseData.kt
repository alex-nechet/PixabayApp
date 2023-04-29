package com.alexnechet.remote.network.model

import com.squareup.moshi.Json

data class ResponseData(
    val total: Long,
    val totalHits: Long,
    @Json(name ="hits")
    val imageList: List<ImageResponse>
)