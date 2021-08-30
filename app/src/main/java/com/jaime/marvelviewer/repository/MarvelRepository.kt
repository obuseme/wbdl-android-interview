package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.model.ComicData
import com.jaime.marvelviewer.util.Constants
import com.jaime.marvelviewer.util.Resource
import com.jaime.marvelviewer.util.Util

class MarvelRepository(private val marvelAPI: MarvelAPI) {
    suspend fun getComicData(): Resource<ComicData> {
        return try {
            val timeStamp = Util.timeStamp
            val response = marvelAPI.getSeries(
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )

            return if(response.isSuccessful) {
                Resource.success(response.body()?.data)
            }
            else {
                Resource.error(response.toString(), null)
            }

        } catch (e: Exception) {
            Resource.error(e.message ?: "", null)
        }
    }
}