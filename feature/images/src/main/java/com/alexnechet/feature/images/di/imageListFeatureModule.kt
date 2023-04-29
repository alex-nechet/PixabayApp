package com.alexnechet.feature.images.di

import com.alexnechet.domain.images.usecase.GetImageByIdUseCase
import com.alexnechet.domain.images.usecase.GetImagesUseCase
import com.alexnechet.feature.images.details.ImageDetailsViewModel
import com.alexnechet.feature.images.list.ImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


private val viewModelsModule = module {
    viewModel { ImageListViewModel() }
    viewModel { ImageDetailsViewModel() }
}

private val useCaseModule = module {
    factory { GetImagesUseCase(get()) }
    factory { GetImageByIdUseCase(get()) }
}

val imagesFeatureModule = useCaseModule + viewModelsModule

