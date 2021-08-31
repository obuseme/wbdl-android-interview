package com.jaime.marvelviewer.di

import android.app.Application
import androidx.room.Room
import com.jaime.marvelviewer.db.ComicDAO
import com.jaime.marvelviewer.db.MarvelDatabase
import com.jaime.marvelviewer.util.Constants.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application): MarvelDatabase {
        return Room.databaseBuilder(application, MarvelDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideComicDao(database: MarvelDatabase): ComicDAO {
        return  database.comicDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideComicDao(get()) }
}