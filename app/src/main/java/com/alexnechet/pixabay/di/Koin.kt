package com.alexnechet.pixabay.di

import com.alexnechet.feature.images.di.imagesFeatureModule
import com.alexnechet.local.images.di.imagesLocalDataSource
import com.alexnechet.pixabay.BuildConfig
import com.alexnechet.remote.di.remoteModule
import com.alexnechet.remote.network.NetworkParameters
import com.alexnechet.repository.images.di.imagesRepositoryModule


private val remoteModule = remoteModule(
    NetworkParameters(
        apiKey = BuildConfig.API_KEY,
        baseUrl = BuildConfig.BASE_URL,
        debuggable = BuildConfig.DEBUG
    )
)

private val localModule = imagesLocalDataSource

private val repositoryModule = imagesRepositoryModule

private val featuresModule = imagesFeatureModule

val pixabayModules = remoteModule + localModule + repositoryModule + featuresModule