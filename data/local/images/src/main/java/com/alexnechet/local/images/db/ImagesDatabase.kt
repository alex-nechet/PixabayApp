package com.alexnechet.local.images.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alexnechet.local.images.model.ImageDb

@Database(entities = [ImageDb::class], version = 1, exportSchema = false)
abstract class ImagesDatabase : RoomDatabase() {
    abstract fun imagesDao(): ImagesDao
}
