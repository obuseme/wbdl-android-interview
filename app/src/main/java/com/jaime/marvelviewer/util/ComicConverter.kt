package com.jaime.marvelviewer.util

import com.jaime.marvelviewer.db.Comic
import com.jaime.marvelviewer.model.ComicData

/**
 * TODO: Temporary Class for converting API Comic Class into Database Comic Class
 * In production full type converters would be used, unifying both 'Comic' Classes
 */
object ComicConverter {

    /**
     * Convert API Comic List to Room DB Comic List
     * @param apiComics The api data containing the list of Comics
     * @return a list of DB Comic Classes
     */
    fun convertToDBComicList(apiComics: ComicData?): List<Comic> {
        val dbComics = mutableListOf<Comic>()
        apiComics?.results?.forEach {
            dbComics.add(
                Comic(
                    0,
                    it.title,
                    it.description,
                    it.thumbnail.getFullImage(),
                    it.startYear,
                    it.endYear,
                    it.rating,
                    it.comics.available
                )
            )
        }

        return dbComics
    }
}