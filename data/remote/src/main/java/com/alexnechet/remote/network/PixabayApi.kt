package com.alexnechet.remote.network

import com.alexnechet.remote.network.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET
    suspend fun getImages(
        @Query("q", encoded = true) query: String?,
        @Query("page") page: Int
    ): ResponseData
}