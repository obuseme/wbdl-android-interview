package com.jaime.marvelviewer

import android.app.Application
import androidx.room.Room
import com.jaime.marvelviewer.db.MarvelDatabase
import com.jaime.marvelviewer.di.*
import com.jaime.marvelviewer.util.Constants.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                appModule,
                databaseModule,
                fragmentModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

}