package com.alexnechet.local.images.di

import androidx.room.Room
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.local.images.ImagesLocalDataSourceImpl
import com.alexnechet.local.images.db.ImagesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userLocalDataSource = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ImagesDatabase::class.java,
            "images.db"
        ).build()
    }

    single { get<ImagesDatabase>().imagesDao() }
    single<ImagesLocalDataSource> { ImagesLocalDataSourceImpl(get()) }
}
