package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.db.SeriesDAO
import com.jaime.marvelviewer.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeriesRepository(
    private val marvelAPI: MarvelAPI,
    private val seriesDAO: SeriesDAO) {

    /**
     * Request series data from API
     * @return a [Resource] with the result of the series data within a list
     */
    suspend fun requestSeriesData(): Resource<List<Series>> {
        return try {
            // API timestamp as per requirements
            val timeStamp = Util.timeStamp

            // Make response to API
            val response = marvelAPI.getAllSeries(
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )

            return if(response.isSuccessful) {

                // If the response has a body containing data, store it in the DB and return the cached results
                response.body()?.let {
                    val results = SeriesConverter.convertToDBSeriesList(it.data)
                    withContext(Dispatchers.IO) {
                        seriesDAO.deleteAll()
                        seriesDAO.insertAll(results)
                    }
                    return getSeriesDataFromDB(true)
                }
                return Resource.error(ErrorCode.NETWORK_ERROR, null)
            }
            else {
                getSeriesDataFromDB(false)
            }

        } catch (e: Exception) {
            getSeriesDataFromDB(false)
        }
    }

    /**
     * Make a request to get the seriess from the local DB
     * @param apiResponseSuccessful a boolean flag to check whether an error message is needed to be passed back or not
     * @return a [Resource] containing the result from the local DB
     */
    private suspend fun getSeriesDataFromDB(apiResponseSuccessful: Boolean): Resource<List<Series>> {
        val seriesData = getDBSeries()
        if(apiResponseSuccessful) return Resource.success(data = seriesData)

        return if(seriesData.isNotEmpty()) {
            return Resource.success(ErrorCode.DB_USING_CACHED_DATA, seriesData)
        }
        else {
            Resource.error(ErrorCode.DB_EMPTY_OR_NULL, null)
        }
    }

    /**
     * Get series data from the DB
     * @return a list of series from the DB
     */
    private suspend fun getDBSeries(): List<Series> {
        return withContext(Dispatchers.IO) {
            seriesDAO.getAll()
        }
    }
}