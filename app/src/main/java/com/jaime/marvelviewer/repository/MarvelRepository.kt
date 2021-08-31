package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.db.Comic
import com.jaime.marvelviewer.db.ComicDAO
import com.jaime.marvelviewer.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarvelRepository(
    private val marvelAPI: MarvelAPI,
    private val comicDAO: ComicDAO) {

    /**
     * Request comic data from API
     * @return a [Resource] with the result of the comic data within a list
     */
    suspend fun requestComicData(): Resource<List<Comic>> {
        return try {
            // API timestamp as per requirements
            val timeStamp = Util.timeStamp

            // Make response to API
            val response = marvelAPI.getSeries(
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )

            return if(response.isSuccessful) {

                // If the response has a body containing data, store it in the DB and return the cached results
                response.body()?.let {
                    val results = ComicConverter.convertToDBComicList(it.data)
                    withContext(Dispatchers.IO) {
                        comicDAO.deleteAll()
                        comicDAO.insertAll(results)
                    }
                    return getComicDataFromDB(true)
                }
                return Resource.error(ErrorCode.NETWORK_ERROR, null)
            }
            else {
               getComicDataFromDB(false)
            }

        } catch (e: Exception) {
            getComicDataFromDB(false)
        }
    }

    /**
     * Make a request to get the Comics from the local DB
     * @param apiResponseSuccessful a boolean flag to check whether an error message is needed to be passed back or not
     * @return a [Resource] containing the result from the local DB
     */
    private suspend fun getComicDataFromDB(apiResponseSuccessful: Boolean): Resource<List<Comic>> {
        val comicData = getDBComics()
        if(apiResponseSuccessful) return Resource.success(data = comicData)

        return if(comicData.isNotEmpty()) {
            return Resource.success(ErrorCode.DB_USING_CACHED_DATA, comicData)
        }
        else {
            Resource.error(ErrorCode.DB_EMPTY_OR_NULL, null)
        }
    }

    /**
     * Get comic data from the DB
     * @return a list of Comics from the DB
     */
    private suspend fun getDBComics(): List<Comic> {
        return withContext(Dispatchers.IO) {
            comicDAO.getAll()
        }
    }
}