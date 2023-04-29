package com.alexnechet.pixabay.di

import com.alexnechet.pixabay.BuildConfig
import com.alexnechet.remote.network.NetworkParameters
import com.alexnechet.remote.network.networkModule
import org.koin.dsl.module


private val remoteModule = module {
    networkModule(NetworkParameters(BuildConfig.API_KEY, BuildConfig.BASE_URL, BuildConfig.DEBUG))
}

val pixabayModules = remoteModule