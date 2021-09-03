package com.jaime.marvelviewer

import android.app.Application
import com.jaime.marvelviewer.di.appModule
import com.jaime.marvelviewer.di.databaseModule
import com.jaime.marvelviewer.di.fragmentModule
import com.jaime.marvelviewer.di.repositoryModule
import com.jaime.marvelviewer.di.viewModelModule
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
