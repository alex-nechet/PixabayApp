package com.alexnechet.remote.di

import com.alexnechet.remote.datasource.images.ImagesRemoteDataSource
import com.alexnechet.remote.datasource.images.ImagesRemoteDataSourceImpl
import com.alexnechet.remote.network.NetworkParameters
import com.alexnechet.remote.network.di.networkModule
import org.koin.dsl.module

private val imagesRemoteDataSource = module {
    single<ImagesRemoteDataSource> { ImagesRemoteDataSourceImpl(api = get()) }
}

fun remoteModule(parameters: NetworkParameters) = listOf(
    imagesRemoteDataSource
) + networkModule(parameters)