package com.jaime.marvelviewer

import com.google.gson.GsonBuilder
import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.di.repositoryModule
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun configureTestAppComponent(baseApi: String) = listOf(
    mockWebServerModule,
    testNetworkModule(baseApi),
    repositoryModule
)

val mockWebServerModule = module {
    factory {
        MockWebServer()
    }
}

/**
 * Build a Test retrofit client and parse using GSON
 */
fun testNetworkModule(baseUrl: String) = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
    factory { get<Retrofit>().create(MarvelAPI::class.java) }
}