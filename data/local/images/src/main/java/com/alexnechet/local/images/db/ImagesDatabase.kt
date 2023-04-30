package com.alexnechet.local.images.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexnechet.local.images.model.ImageDb
import com.alexnechet.local.images.model.RemoteKeys

@Database(
    entities = [ImageDb::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}
