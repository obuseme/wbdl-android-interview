package com.jaime.marvelviewer.api

import com.jaime.marvelviewer.model.ComicAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("/series")
    fun getSeries(
        @Query("apiKey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): Response<ComicAPIResponse>
}