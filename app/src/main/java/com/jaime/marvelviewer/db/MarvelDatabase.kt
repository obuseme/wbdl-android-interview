package com.jaime.marvelviewer.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Comic::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun comicDao(): ComicDAO
}