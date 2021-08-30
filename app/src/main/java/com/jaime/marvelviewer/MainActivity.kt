package com.jaime.marvelviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jaime.marvelviewer.di.appModule
import com.jaime.marvelviewer.di.repositoryModule
import com.jaime.marvelviewer.di.viewModelModule
import com.jaime.marvelviewer.ui.MarvelSeriesViewModel
import com.jaime.marvelviewer.util.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject

class MainActivity : AppCompatActivity() {
    val viewModel: MarvelSeriesViewModel by inject(MarvelSeriesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(
                appModule,
                repositoryModule,
                viewModelModule
            )
        }

        init()
    }

    private fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.comicData.collect {
                when(it.status) {
                    Status.SUCCESS -> {
                        val a = ""
                    }
                    Status.ERROR -> {
                        val a = ""
                    }
                    Status.LOADING -> {
                        val a = ""
                    }
                }
            }
        }
    }
}