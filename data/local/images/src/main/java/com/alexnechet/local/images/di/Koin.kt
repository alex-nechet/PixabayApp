package com.alexnechet.local.images.di

import androidx.room.Room
import com.alexnechet.local.images.ImagesLocalDataSource
import com.alexnechet.local.images.ImagesLocalDataSourceImpl
import com.alexnechet.local.images.RemoteKeysLocalDataSource
import com.alexnechet.local.images.RemoteKeysLocalDataSourceImpl
import com.alexnechet.local.images.db.ImagesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val imagesLocalDataSource = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ImagesDatabase::class.java,
            "images.db"
        ).build()
    }

    single<ImagesLocalDataSource> {
        ImagesLocalDataSourceImpl(dao= get<ImagesDatabase>().imagesDao())
    }

    single<RemoteKeysLocalDataSource> {
        RemoteKeysLocalDataSourceImpl(dao = get<ImagesDatabase>().remoteKeysDao())
    }
}
