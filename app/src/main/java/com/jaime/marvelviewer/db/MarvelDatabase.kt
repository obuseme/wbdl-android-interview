package com.jaime.marvelviewer.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Series::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun seriesDAO(): SeriesDAO
}
