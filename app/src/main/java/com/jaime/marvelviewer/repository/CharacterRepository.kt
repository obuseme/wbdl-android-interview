package com.jaime.marvelviewer.repository

import com.jaime.marvelviewer.api.MarvelAPI
import com.jaime.marvelviewer.model.character.Character
import com.jaime.marvelviewer.util.*

class CharacterRepository(private val marvelAPI: MarvelAPI) {
    /**
     */
    suspend fun requestCharacterData(id: String): Resource<List<Character>> {
        return try {
            // API timestamp as per requirements
            val timeStamp = Util.timeStamp

            // Make response to API
            val response = marvelAPI.getCharacter(
                characterId = id,
                apiKey = Constants.API_KEY,
                timeStamp = timeStamp,
                hash = Util.getMD5Hash(timeStamp)
            )

            return if(response.isSuccessful) {

                // If the response has a body containing data, store it in the DB and return the cached results
                response.body()?.let {
                    return Resource.success(data = it.data.results)
                }
                return Resource.error(ErrorCode.NETWORK_ERROR, null)
            }
            else {
                return Resource.error(ErrorCode.NETWORK_ERROR, null)
            }

        } catch (e: Exception) {
            return Resource.error(ErrorCode.NETWORK_ERROR, null)
        }
    }
}