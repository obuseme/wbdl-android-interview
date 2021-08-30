package com.jaime.marvelviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaime.marvelviewer.di.appModule
import com.jaime.marvelviewer.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                appModule,
                repositoryModule
            )
        }
    }
}