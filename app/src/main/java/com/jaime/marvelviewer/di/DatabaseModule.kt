package com.jaime.marvelviewer.di

import android.app.Application
import androidx.room.Room
import com.jaime.marvelviewer.db.MarvelDatabase
import com.jaime.marvelviewer.db.SeriesDAO
import com.jaime.marvelviewer.util.Constants.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MarvelDatabase {
        return Room.databaseBuilder(application, MarvelDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideSeriesDao(database: MarvelDatabase): SeriesDAO {
        return  database.seriesDAO()
    }

    single { provideDatabase(androidApplication()) }
    single { provideSeriesDao(get()) }
}