package com.jaime.marvelviewer.api

import com.jaime.marvelviewer.model.character.CharacterAPIResponse
import com.jaime.marvelviewer.model.comic.ComicAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @GET("v1/public/series")
    suspend fun getAllSeries(
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): Response<ComicAPIResponse>

    @GET("v1/public/series/{characterId}/characters")
    suspend fun getCharacter(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String,
        @Query("ts") timeStamp: String,
        @Query("hash") hash: String
    ): Response<CharacterAPIResponse>
}