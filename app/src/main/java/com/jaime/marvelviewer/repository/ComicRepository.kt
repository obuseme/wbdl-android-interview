package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.model.comic.Comic
import com.jaime.marvelviewer.util.*

class ComicRepository(private val marvelAPI: MarvelAPI) {
    /**
     */
    suspend fun requestComicData(id: String): Resource<List<Comic>> {
        return try {
            // API timestamp as per requirements
            val timeStamp = Util.timeStamp

            // Make response to API
            val response = marvelAPI.getComic(
                seriesId = id,
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )

            return if(response.isSuccessful) {
                response.body()?.let {
                    return Resource.success(data = it.data.results)
                }
                return Resource.error(ErrorCode.NETWORK_ERROR, null)
            }
            else {
                Resource.error(ErrorCode.NETWORK_ERROR, null)
            }

        } catch (e: Exception) {
            Resource.error(ErrorCode.NETWORK_ERROR, null)
        }
    }
}