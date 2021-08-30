package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.model.ComicData
import com.jaime.marvelviewer.util.Constants
import com.jaime.marvelviewer.util.Resource
import com.jaime.marvelviewer.util.Util

class MarvelRepository(private val request: MarvelAPI) {
    suspend fun getComicData(): Resource<ComicData> {
        return try {
            val timeStamp = Util.timeStamp
            val response = request.getSeries(
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )
            Resource.success(response.body()?.data)
        } catch (e: Exception) {
            Resource.error(e.message ?: "", null)
        }
    }
}