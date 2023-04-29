package com.alexnechet.pixabay

import android.app.Application
import com.alexnechet.pixabay.di.pixabayModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class PixabayApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PixabayApp)
            modules(pixabayModules)
        }
    }
}