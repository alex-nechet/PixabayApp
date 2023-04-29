package com.alexnechet.pixabay.di

import com.alexnechet.pixabay.BuildConfig
import com.alexnechet.remote.di.remoteModule
import com.alexnechet.remote.network.NetworkParameters


private val remoteModule = remoteModule(
    NetworkParameters(
        apiKey = BuildConfig.API_KEY,
        baseUrl = BuildConfig.BASE_URL,
        debuggable = BuildConfig.DEBUG
    )
)

val pixabayModules = remoteModule