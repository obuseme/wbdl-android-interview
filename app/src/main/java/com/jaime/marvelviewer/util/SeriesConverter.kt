package com.jaime.marvelviewer.util

import com.jaime.marvelviewer.db.Series
import com.jaime.marvelviewer.model.series.SeriesData

/**
 * TODO: Temporary Class for converting API Comic Class into Database Comic Class
 * In production full type converters would be used, unifying both 'Comic' Classes
 */
object SeriesConverter {

    /**
     * Convert API Series List to Room DB Comic List
     * @param apiSeries The api data containing the list of Comics
     * @return a list of DB Comic Classes
     */
    fun convertToDBSeriesList(apiSeries: SeriesData?): List<Series> {
        val dbComics = mutableListOf<Series>()
        apiSeries?.results?.forEach {
            dbComics.add(
                Series(
                    it.id,
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
