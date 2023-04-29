package com.alexnechet.repository.images.di

import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.repository.images.ImagesRepositoryImpl
import org.koin.dsl.module


val imagesRepositoryModule = module {
    single<ImagesRepository> { ImagesRepositoryImpl(get(), get()) }
}